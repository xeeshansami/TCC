package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.CustomerDetailsResponse;

public interface CustomerDetailsCallBack {
    void Success(CustomerDetailsResponse response);

    void Failure(BaseResponse baseResponse);
}
