package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ForgetPasswordResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.UpdateProfileResponse;

public interface ForgetPasswordCallBack {
    void Success(ForgetPasswordResponse response);

    void Failure(BaseResponse baseResponse);
}
