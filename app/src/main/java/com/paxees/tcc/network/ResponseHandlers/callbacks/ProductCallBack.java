package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.AddtoCartResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ProductResponse;

public interface ProductCallBack {
    void Success(ProductResponse response);

    void Failure(BaseResponse baseResponse);
}
