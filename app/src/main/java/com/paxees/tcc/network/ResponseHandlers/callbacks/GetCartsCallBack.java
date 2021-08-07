package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetAddToCartResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

public interface GetCartsCallBack {
    void Success(GetAddToCartResponse response);

    void Failure(BaseResponse baseResponse);
}
