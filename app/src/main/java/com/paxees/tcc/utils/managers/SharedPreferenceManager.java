package com.paxees.tcc.utils.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paxees.tcc.network.networkmodels.response.baseResponses.LoginResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by zeeshan on 05-25-2021
 */

public class SharedPreferenceManager {
    Object object = new Object();
    //Store and Retrieve
    public static final String COUNT = "COUNT";
    public static final String OBJECT_KEY = "OBJECT_KEY";
    public static final String DARK_MODE = "DARK_MODE";
    public static final String LOGIN_KEY = "LOGIN_KEY";
    //Is Fingerprint Authentication Enabled
    public static SharedPreferences sSharedPreferences;
    public static final SharedPreferenceManager sharedPrefManagerInstance = new SharedPreferenceManager();

    public SharedPreferenceManager getInstance(Context context) {
        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPrefManagerInstance;
    }

    public SharedPreferenceManager() {
    }

    public void storeStringInSharedPreferences(String key, String content) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(key, content);
        editor.apply();
    }

    public String getStringFromSharedPreferences(String key) {
        return sSharedPreferences.getString(key, "");
    }

    public void removeStringInSharedPreferences(String key, String remove) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        if (key.equals("logout")) {
            editor.clear();
            editor.commit();
        } else if (remove.equals("remove")) {
            editor.remove(key).commit();
        }
    }

    public void clearSP() {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.clear().commit();
    }

    public void storeLongInSharedPreferences(String key, long content) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putLong(key, content);
        editor.apply();
    }

    public long getLongFromSharedPreferences(String key) {
        return sSharedPreferences.getLong(key, 0L);
    }

    public static void storeBooleanInSharedPreferences(String key, boolean status) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putBoolean(key, status);
        editor.apply();
    }

    public boolean getBooleanFromSharedPreferences(String key) {
        return sSharedPreferences.getBoolean(key, false);
    }

    public void storeFloatInSharedPreferences(String key, float content) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putFloat(key, content);
        editor.apply();
    }

    public float getFloatFromSharedPreferences(String key) {
        return sSharedPreferences.getFloat(key, 0.0F);
    }


    public void storeIntInSharedPreferences(String key, int content) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putInt(key, content);
        editor.apply();
    }

    public int getIntFromSharedPreferences(String key) {
        return sSharedPreferences.getInt(key, 0);
    }


    public <Object> void setObjectList(ArrayList<Object> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        setObject(json);
    }

    public void setObject(String value) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(OBJECT_KEY, value);
        editor.commit();
    }

    public ArrayList<Object> getObjectList() {
        ArrayList<Object> companyList = new ArrayList<>();
        String json = new Gson().toJson(companyList);
        if (sSharedPreferences != null) {
            Gson gson = new Gson();
            String string = sSharedPreferences.getString(OBJECT_KEY, json);
            Type type = new TypeToken<ArrayList<Object>>() {
            }.getType();
            companyList = gson.fromJson(string, type);
            return companyList;
        }
        return companyList;
    }
 public void setLoginData(LoginResponse data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(LOGIN_KEY, json);
        editor.commit();
    }

    public LoginResponse getLoginData() {
        LoginResponse companyList = new LoginResponse();
        String json = new Gson().toJson(companyList);
        if (sSharedPreferences != null) {
            Gson gson = new Gson();
            String string = sSharedPreferences.getString(LOGIN_KEY, json);
            Type type = new TypeToken<LoginResponse>() {
            }.getType();
            companyList = gson.fromJson(string, type);
            return companyList;
        }
        return companyList;
    }
    /*public void setLovDoctype(ArrayList<DocsData> data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(LOV_DOCTYPE, json);
        editor.commit();
    }

    public ArrayList<DocsData> getLovDoctype() {
        ArrayList<DocsData> companyList = new ArrayList<DocsData>();
        String json = new Gson().toJson(companyList);
        if (sSharedPreferences != null) {
            Gson gson = new Gson();
            String string = sSharedPreferences.getString(LOV_DOCTYPE, json);
            Type type = new TypeToken<ArrayList<DocsData>>() {
            }.getType();
            companyList = gson.fromJson(string, type);
            return companyList;
        }
        return companyList;
    }*/


    public void setIntCount(int value) {
        storeIntInSharedPreferences(COUNT, value);
    }

    public int getIntCount() {
        //If value is less than 0
        if (getIntFromSharedPreferences(COUNT) < 0) {
            setIntCount(0);
            return 0;
        } else {
            //else return whole value
            return getIntFromSharedPreferences(COUNT);
        }
    }
}
