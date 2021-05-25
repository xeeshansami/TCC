package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.RegisterCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;

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
