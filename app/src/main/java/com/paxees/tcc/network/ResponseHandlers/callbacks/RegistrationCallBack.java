package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.RegistrationResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.UpdateCartResponse;

public interface RegistrationCallBack {
    void Success(RegistrationResponse response);

    void Failure(BaseResponse baseResponse);
}
