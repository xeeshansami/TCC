package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.RemoveProdCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;

import retrofit2.Response;

public class RemoveProdBaseHR extends BaseRH<String> {

    RemoveProdCallBack callBack;

    public RemoveProdBaseHR(RemoveProdCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<String> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
