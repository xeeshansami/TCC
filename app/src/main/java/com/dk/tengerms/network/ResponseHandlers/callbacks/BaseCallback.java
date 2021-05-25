package com.dk.tengerms.network.ResponseHandlers.callbacks;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BaseResponse;


public interface BaseCallback {
    void onSuccess(BaseResponse response);

    void onFailure(BaseResponse response);
}
