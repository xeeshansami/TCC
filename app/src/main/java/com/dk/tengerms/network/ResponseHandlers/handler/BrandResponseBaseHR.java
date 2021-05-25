package com.dk.tengerms.network.ResponseHandlers.handler;

import com.dk.tengerms.network.ResponseHandlers.callbacks.BrandByCategoryCallBack;
import com.dk.tengerms.network.ResponseHandlers.callbacks.BrandResponseCallBack;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BaseResponse;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BrandByCategoryResponse;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BrandDetailResponse;

import retrofit2.Response;

public class BrandResponseBaseHR extends BaseRH<BrandByCategoryResponse> {

    BrandResponseCallBack callBack;

    public BrandResponseBaseHR(BrandResponseCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<BrandByCategoryResponse> response) {
        callBack.BrandResponseSuccess(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.BrandResponseFailure(response);
    }
}
