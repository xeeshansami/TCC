package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.WishlistImageCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.WishlistImageResponse;

import retrofit2.Response;

public class WishlistImageBaseHR extends BaseRH<WishlistImageResponse> {

    WishlistImageCallBack callBack;

    public WishlistImageBaseHR(WishlistImageCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<WishlistImageResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
