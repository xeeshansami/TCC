package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.RegistrationCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.RegistrationResponse;

import retrofit2.Response;

public class RegisterBaseHR extends BaseRH<RegistrationResponse> {

    RegistrationCallBack callBack;

    public RegisterBaseHR(RegistrationCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<RegistrationResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
