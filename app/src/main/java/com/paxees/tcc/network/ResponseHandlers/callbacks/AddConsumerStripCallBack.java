package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.AddConsumerStripResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

public interface AddConsumerStripCallBack {
    void Success(AddConsumerStripResponse response);

    void Failure(BaseResponse baseResponse);
}
