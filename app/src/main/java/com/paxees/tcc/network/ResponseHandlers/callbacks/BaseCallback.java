package com.paxees.tcc.network.ResponseHandlers.callbacks;


import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;

public interface BaseCallback {
    void onSuccess(BaseResponse response);

    void onFailure(BaseResponse response);
}
