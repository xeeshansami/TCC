package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandByCategoryResponse;

public interface BrandResponseCallBack {
    void BrandResponseSuccess(BrandByCategoryResponse response);

    void BrandResponseFailure(BaseResponse baseResponse);
}
