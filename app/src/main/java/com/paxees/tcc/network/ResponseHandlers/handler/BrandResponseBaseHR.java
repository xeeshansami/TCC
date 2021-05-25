package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.BrandResponseCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandByCategoryResponse;

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
