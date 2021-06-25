package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.CategoriesCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.CategoriesResponse;

import retrofit2.Response;

public class CategoriesBaseHR extends BaseRH<CategoriesResponse> {

    CategoriesCallBack callBack;

    public CategoriesBaseHR(CategoriesCallBack loginCallBack) {
        this.callBack = loginCallBack;
    }

    @Override
    protected void onSuccess(Response<CategoriesResponse> response) {
        callBack.CategoriesSuccess(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.CategoriesFailure(response);
    }
}
