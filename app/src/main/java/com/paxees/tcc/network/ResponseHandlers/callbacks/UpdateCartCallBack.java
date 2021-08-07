package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.UpdateCartResponse;

public interface UpdateCartCallBack {
    void Success(UpdateCartResponse response);

    void Failure(BaseResponse baseResponse);
}
