package com.paxees.tcc.network.ResponseHandlers.handler;


import com.google.gson.Gson;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.utils.GlobalClass;
import com.paxees.tcc.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class BaseRH<T> implements Callback<T> {
    GlobalClass globalClass = (GlobalClass) GlobalClass.applicationContext.getApplicationContext();

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(response);
        } else {
            ToastUtils.normalShowToast(globalClass.getApplicationContext(),"Something went wrong, try again later",2000);
            globalClass.hideLoader();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t.getMessage().equalsIgnoreCase("timeout")) {
            /*When server no response in 30 seconds*/
//            onFailure(new BaseResponse("-1", false, "Connection timeout, please check your internet connection, please try again "));
            globalClass.hideLoader();
        } else {
            if (t instanceof SocketTimeoutException) {
                /*When server no response in 30 seconds*/
//                onFailure(new BaseResponse("-1", false, "Connection timeout, please check your internet connection, please try again "));
                globalClass.hideLoader();
            } else {
                /*When something unexpected error occurred.*/
                globalClass.hideLoader();
            }
        }
    }

    protected abstract void onSuccess(Response<T> response);

    protected abstract void onFailure(BaseResponse response);

}