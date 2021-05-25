package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandDetailResponse;

public interface BrandByCategoryCallBack {
    void BrandByCategorySuccess(BrandDetailResponse response);

    void BrandByCategoryFailure(BaseResponse baseResponse);
}
