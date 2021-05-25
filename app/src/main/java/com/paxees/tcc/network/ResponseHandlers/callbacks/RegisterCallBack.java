package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;

public interface RegisterCallBack {
    void RegisterSuccess(BaseResponse response);

    void RegisterFailure(BaseResponse baseResponse);
}
