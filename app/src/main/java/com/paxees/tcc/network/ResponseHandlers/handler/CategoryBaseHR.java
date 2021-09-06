package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.CategoryCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.models.CategoriesResponse;

import retrofit2.Response;

public class CategoryBaseHR extends BaseRH<CategoriesResponse> {

    CategoryCallBack callBack;

    public CategoryBaseHR(CategoryCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<CategoriesResponse> response) {
        callBack.CategorySuccess(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.CategoryFailure(response);
    }
}
