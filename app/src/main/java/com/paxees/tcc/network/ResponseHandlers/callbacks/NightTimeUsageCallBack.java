package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.NightTimeUsuageResponse;

public interface NightTimeUsageCallBack {
    void Success(NightTimeUsuageResponse response);

    void Failure(BaseResponse baseResponse);
}
