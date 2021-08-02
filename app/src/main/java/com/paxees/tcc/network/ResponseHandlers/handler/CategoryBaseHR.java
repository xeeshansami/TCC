package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.CategoryCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.CategoryResponse;

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
