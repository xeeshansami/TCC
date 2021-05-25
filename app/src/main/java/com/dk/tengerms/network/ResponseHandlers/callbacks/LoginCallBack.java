package com.dk.tengerms.network.ResponseHandlers.callbacks;

import com.dk.tengerms.network.networkmodels.response.baseResponses.BaseResponse;
import com.dk.tengerms.network.networkmodels.response.baseResponses.LoginResponse;

public interface LoginCallBack {
    void LoginSuccess(LoginResponse loginResponse);

    void LoginFailure(BaseResponse baseResponse);
}
