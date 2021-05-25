package com.dk.tengerms.network.ResponseHandlers.callbacks;

import com.dk.tengerms.network.networkmodels.response.baseResponses.CategoryResponse;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BaseResponse;

public interface CategoryCallBack {
    void CategorySuccess(CategoryResponse response);

    void CategoryFailure(BaseResponse baseResponse);
}
