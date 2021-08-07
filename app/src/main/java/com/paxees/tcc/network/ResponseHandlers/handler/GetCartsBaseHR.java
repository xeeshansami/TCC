package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.GetCartsCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetAddToCartResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class GetCartsBaseHR extends BaseRH<GetAddToCartResponse> {

    GetCartsCallBack callBack;

    public GetCartsBaseHR(GetCartsCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<GetAddToCartResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
