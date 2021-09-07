package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetPaymentMethodListOfConsumerResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

public interface GetPaymentMethodConusmerCallBack {
    void Success(GetPaymentMethodListOfConsumerResponse response);

    void Failure(BaseResponse baseResponse);
}
