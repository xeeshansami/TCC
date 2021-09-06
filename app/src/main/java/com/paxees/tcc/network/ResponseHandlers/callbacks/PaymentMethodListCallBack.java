package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PaymentMethodListResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

public interface PaymentMethodListCallBack {
    void Success(PaymentMethodListResponse response);

    void Failure(BaseResponse baseResponse);
}
