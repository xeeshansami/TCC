package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.LoginResponse;

public interface LoginCallBack {
    void LoginSuccess(LoginResponse loginResponse);

    void LoginFailure(BaseResponse baseResponse);
}
