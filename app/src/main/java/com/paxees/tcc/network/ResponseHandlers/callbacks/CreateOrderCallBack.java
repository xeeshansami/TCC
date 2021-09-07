package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.CreateOrderResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

public interface CreateOrderCallBack {
    void Success(CreateOrderResponse response);

    void Failure(BaseResponse baseResponse);
}
