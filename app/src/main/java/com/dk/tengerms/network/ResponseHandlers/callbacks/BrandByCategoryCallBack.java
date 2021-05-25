package com.dk.tengerms.network.ResponseHandlers.callbacks;

import com.dk.tengerms.network.networkmodels.response.baseResponses.BaseResponse;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BrandByCategoryResponse;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BrandDetailResponse;

public interface BrandByCategoryCallBack {
    void BrandByCategorySuccess(BrandDetailResponse response);

    void BrandByCategoryFailure(BaseResponse baseResponse);
}
