package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.VideosListCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.VideosListResponse;

import retrofit2.Response;

public class VideosListBaseHR extends BaseRH<VideosListResponse> {

    VideosListCallBack callBack;

    public VideosListBaseHR(VideosListCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<VideosListResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
