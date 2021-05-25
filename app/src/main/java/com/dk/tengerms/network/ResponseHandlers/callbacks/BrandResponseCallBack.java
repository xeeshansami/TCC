package com.dk.tengerms.network.ResponseHandlers.callbacks;

import com.dk.tengerms.network.networkmodels.response.baseResponses.BaseResponse;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BrandByCategoryResponse;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BrandDetailResponse;

public interface BrandResponseCallBack {
    void BrandResponseSuccess(BrandByCategoryResponse response);

    void BrandResponseFailure(BaseResponse baseResponse);
}
