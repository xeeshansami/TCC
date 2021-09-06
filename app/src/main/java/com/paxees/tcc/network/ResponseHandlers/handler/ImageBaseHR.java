package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.ImageCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ImageResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class ImageBaseHR extends BaseRH<ImageResponse> {

    ImageCallBack callBack;

    public ImageBaseHR(ImageCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<ImageResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
