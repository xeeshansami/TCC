package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.models.CategoriesResponse;

public interface CategoryCallBack {
    void CategorySuccess(CategoriesResponse response);

    void CategoryFailure(BaseResponse baseResponse);
}
