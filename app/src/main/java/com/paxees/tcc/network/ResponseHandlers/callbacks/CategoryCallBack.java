package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.CategoryResponse;

public interface CategoryCallBack {
    void CategorySuccess(CategoryResponse response);

    void CategoryFailure(BaseResponse baseResponse);
}
