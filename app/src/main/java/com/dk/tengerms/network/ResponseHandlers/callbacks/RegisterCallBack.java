package com.dk.tengerms.network.ResponseHandlers.callbacks;

import com.dk.tengerms.network.networkmodels.request.RegisterRequest;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BaseResponse;
import com.dk.tengerms.network.networkmodels.response.baseResponses.LoginResponse;

public interface RegisterCallBack {
    void RegisterSuccess(BaseResponse response);

    void RegisterFailure(BaseResponse baseResponse);
}
