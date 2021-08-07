package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.request.AddToCartRequest;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddtoCartResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;

public interface AddToCartCallBack {
    void Success(AddtoCartResponse response);

    void Failure(BaseResponse baseResponse);
}
