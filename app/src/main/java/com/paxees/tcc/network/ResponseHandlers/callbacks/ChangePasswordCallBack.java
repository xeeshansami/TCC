package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ChangePasswordResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.UpdateCartResponse;

public interface ChangePasswordCallBack {
    void Success(ChangePasswordResponse response);

    void Failure(BaseResponse baseResponse);
}
