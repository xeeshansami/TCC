package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.BrandByCategoryCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandDetailResponse;

import retrofit2.Response;

public class BrandByCategoryBaseHR extends BaseRH<BrandDetailResponse> {

    BrandByCategoryCallBack callBack;

    public BrandByCategoryBaseHR(BrandByCategoryCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<BrandDetailResponse> response) {
        callBack.BrandByCategorySuccess(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.BrandByCategoryFailure(response);
    }
}
