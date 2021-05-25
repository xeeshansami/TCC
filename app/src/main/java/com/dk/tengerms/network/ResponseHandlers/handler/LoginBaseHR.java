package com.dk.tengerms.network.ResponseHandlers.handler;

import com.dk.tengerms.network.ResponseHandlers.callbacks.LoginCallBack;
import com.dk.tengerms.network.networkmodels.response.baseResponses.LoginResponse;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BaseResponse;

import retrofit2.Response;

public class LoginBaseHR extends BaseRH<LoginResponse> {

    LoginCallBack loginCallBack;

    public LoginBaseHR(LoginCallBack loginCallBack) {
        this.loginCallBack = loginCallBack;
    }

    @Override
    protected void onSuccess(Response<LoginResponse> response) {
        loginCallBack.LoginSuccess(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        loginCallBack.LoginFailure(response);
    }
}
