package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.ProductSearchCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ProductSearchResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class ProductSearchBaseHR extends BaseRH<ProductSearchResponse> {

    ProductSearchCallBack callBack;

    public ProductSearchBaseHR(ProductSearchCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<ProductSearchResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
