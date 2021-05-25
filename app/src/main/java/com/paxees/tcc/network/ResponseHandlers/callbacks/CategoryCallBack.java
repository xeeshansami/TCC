package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.CategoryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;

public interface CategoryCallBack {
    void CategorySuccess(CategoryResponse response);

    void CategoryFailure(BaseResponse baseResponse);
}
