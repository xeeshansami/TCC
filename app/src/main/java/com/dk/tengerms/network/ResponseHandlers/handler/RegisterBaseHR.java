package com.dk.tengerms.network.ResponseHandlers.handler;

import com.dk.tengerms.network.ResponseHandlers.callbacks.LoginCallBack;
import com.dk.tengerms.network.ResponseHandlers.callbacks.RegisterCallBack;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BaseResponse;
import com.dk.tengerms.network.networkmodels.response.baseResponses.LoginResponse;

import retrofit2.Response;

public class RegisterBaseHR extends BaseRH<BaseResponse> {

    RegisterCallBack callBack;

    public RegisterBaseHR(RegisterCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<BaseResponse> response) {
        callBack.RegisterSuccess(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.RegisterFailure(response);
    }
}
