package com.dk.tengerms.network.ResponseHandlers.handler;

import com.dk.tengerms.network.ResponseHandlers.callbacks.BaseCallback;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BaseResponse;

import retrofit2.Response;

public class BaseResponseHR extends BaseRH<BaseResponse> {

    BaseCallback callback;

    public BaseResponseHR(BaseCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onSuccess(Response<BaseResponse> response) {
        callback.onSuccess(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callback.onFailure(response);
    }
}
