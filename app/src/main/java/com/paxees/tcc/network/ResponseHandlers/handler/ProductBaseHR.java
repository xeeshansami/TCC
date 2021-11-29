package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.AddToCartCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.ProductCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddtoCartResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ProductResponse;

import retrofit2.Response;

public class ProductBaseHR extends BaseRH<ProductResponse> {

    ProductCallBack callBack;

    public ProductBaseHR(ProductCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<ProductResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
