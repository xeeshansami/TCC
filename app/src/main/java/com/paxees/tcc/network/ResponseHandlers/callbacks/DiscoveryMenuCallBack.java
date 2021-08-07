package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.DiscoveryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

public interface DiscoveryMenuCallBack {
    void Success(DiscoveryResponse response);

    void Failure(BaseResponse baseResponse);
}
