package com.dk.tengerms.network.ResponseHandlers.handler;

import com.dk.tengerms.network.ResponseHandlers.callbacks.CategoryCallBack;
import com.dk.tengerms.network.networkmodels.response.baseResponses.CategoryResponse;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BaseResponse;

import retrofit2.Response;

public class CategoryBaseHR extends BaseRH<CategoryResponse> {

    CategoryCallBack callBack;

    public CategoryBaseHR(CategoryCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<CategoryResponse> response) {
        callBack.CategorySuccess(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.CategoryFailure(response);
    }
}
