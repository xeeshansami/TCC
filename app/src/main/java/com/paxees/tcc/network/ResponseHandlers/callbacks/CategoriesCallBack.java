package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.CategoriesResponse;

public interface CategoriesCallBack {
    void CategoriesSuccess(CategoriesResponse response);

    void CategoriesFailure(BaseResponse baseResponse);
}
