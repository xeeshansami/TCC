package com.paxees.tcc.utils.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddNewConsumerResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddNewCreditCardResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.CardAddedInConsumerStripeResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.CustomerDetailsResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.DataXX;
import com.paxees.tcc.network.networkmodels.response.baseResponses.DataXXX;
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetExistingConsumerList;
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
    public static final String CUSTOMER_DETAILS_KEY = "CUSTOMER_DETAILS_KEY";
    public static final String ZOHO_KEY = "ZOHO_KEY";
    public static final String CARD_STRIPE = "CARD_STRIPE";
    public static final String CONSUMER_STRIPE = "CONSUMER_STRIPE";
    public static final String EXISTING_CONSUMER_STRIPE = "EXISTING_CONSUMER_STRIPE";
    public static final String ADDRESS_USE = "ADDRESS_USE";
    public static final String SINGLE_CONSUMER_STRIPE = "SINGLE_CONSUMER_STRIPE";
    public static final String SAVED_CARD_INFO = "SAVED_CARD_INFO";
    public static final String CARD_ADDED_IN_CONSUMER_STRIPE = "CARD_ADDED_IN_CONSUMER_STRIPE";
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

    public void setAddressUse(String value) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(ADDRESS_USE, value);
        editor.commit();
    }

    public Object getAddressUse() {
        Object companyList = new Object();
        String json = new Gson().toJson(companyList);
        if (sSharedPreferences != null) {
            Gson gson = new Gson();
            String string = sSharedPreferences.getString(ADDRESS_USE, json);
            Type type = new TypeToken<Object>() {
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
    public void setCardInStripe(AddNewCreditCardResponse data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(CARD_STRIPE, json);
        editor.commit();
    }

    public AddNewCreditCardResponse getCardFromStripe() {
        AddNewCreditCardResponse companyList = new AddNewCreditCardResponse();
        String json = new Gson().toJson(companyList);
        if (sSharedPreferences != null) {
            Gson gson = new Gson();
            String string = sSharedPreferences.getString(CARD_STRIPE, json);
            Type type = new TypeToken<AddNewCreditCardResponse>() {
            }.getType();
            companyList = gson.fromJson(string, type);
            return companyList;
        }
        return companyList;
    }

 public void setCardAddedInConsumerStripe(CardAddedInConsumerStripeResponse data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(CARD_ADDED_IN_CONSUMER_STRIPE, json);
        editor.commit();
    }

    public CardAddedInConsumerStripeResponse getCardAddedInConsumerStripe() {
        CardAddedInConsumerStripeResponse companyList = new CardAddedInConsumerStripeResponse();
        String json = new Gson().toJson(companyList);
        if (sSharedPreferences != null) {
            Gson gson = new Gson();
            String string = sSharedPreferences.getString(CARD_ADDED_IN_CONSUMER_STRIPE, json);
            Type type = new TypeToken<CardAddedInConsumerStripeResponse>() {
            }.getType();
            companyList = gson.fromJson(string, type);
            return companyList;
        }
        return companyList;
    }

    public void setConsumerInStripe(AddNewConsumerResponse data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(CONSUMER_STRIPE, json);
        editor.commit();
    }

    public AddNewConsumerResponse getConsumerFromStripe() {
        AddNewConsumerResponse companyList = new AddNewConsumerResponse();
        String json = new Gson().toJson(companyList);
        if (sSharedPreferences != null) {
            Gson gson = new Gson();
            String string = sSharedPreferences.getString(CONSUMER_STRIPE, json);
            Type type = new TypeToken<AddNewConsumerResponse>() {
            }.getType();
            companyList = gson.fromJson(string, type);
            return companyList;
        }
        return companyList;
    }


    public void setExistingConsumerInStripe(GetExistingConsumerList data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(EXISTING_CONSUMER_STRIPE, json);
        editor.commit();
    }

    public GetExistingConsumerList getExistingConsumerFromStripe() {
        GetExistingConsumerList companyList = new GetExistingConsumerList();
        String json = new Gson().toJson(companyList);
        if (sSharedPreferences != null) {
            Gson gson = new Gson();
            String string = sSharedPreferences.getString(EXISTING_CONSUMER_STRIPE, json);
            Type type = new TypeToken<GetExistingConsumerList>() {
            }.getType();
            companyList = gson.fromJson(string, type);
            return companyList;
        }
        return companyList;
    }

  public void setSingleConsumer(DataXXX data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(SINGLE_CONSUMER_STRIPE, json);
        editor.commit();
    }

    public DataXXX getSingleConsumer() {
        DataXXX companyList = new DataXXX();
        String json = new Gson().toJson(companyList);
        if (sSharedPreferences != null) {
            Gson gson = new Gson();
            String string = sSharedPreferences.getString(SINGLE_CONSUMER_STRIPE, json);
            Type type = new TypeToken<DataXXX>() {
            }.getType();
            companyList = gson.fromJson(string, type);
            return companyList;
        }
        return companyList;
    }

    public void setCardSave(DataXX data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(SAVED_CARD_INFO, json);
        editor.commit();
    }

    public DataXX getCardSaved() {
        DataXX companyList = new DataXX();
        String json = new Gson().toJson(companyList);
        if (sSharedPreferences != null) {
            Gson gson = new Gson();
            String string = sSharedPreferences.getString(SAVED_CARD_INFO, json);
            Type type = new TypeToken<DataXX>() {
            }.getType();
            companyList = gson.fromJson(string, type);
            return companyList;
        }
        return companyList;
    }


    public void setCustomerDetails(CustomerDetailsResponse data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(CUSTOMER_DETAILS_KEY, json);
        editor.commit();
    }

    public CustomerDetailsResponse getCustomerDetails() {
        CustomerDetailsResponse companyList = new CustomerDetailsResponse();
        String json = new Gson().toJson(companyList);
        if (sSharedPreferences != null) {
            Gson gson = new Gson();
            String string = sSharedPreferences.getString(CUSTOMER_DETAILS_KEY, json);
            Type type = new TypeToken<CustomerDetailsResponse>() {
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
