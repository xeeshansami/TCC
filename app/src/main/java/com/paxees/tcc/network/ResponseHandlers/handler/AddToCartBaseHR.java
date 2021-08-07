package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.AddToCartCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddtoCartResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class AddToCartBaseHR extends BaseRH<AddtoCartResponse> {

    AddToCartCallBack callBack;

    public AddToCartBaseHR(AddToCartCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<AddtoCartResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
