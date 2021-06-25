package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.LoginCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.LoginResponse;

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
