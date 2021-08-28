package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainRequestTokenResponse;

public interface StrainRequestTokenCallBack {
    void Success(StrainRequestTokenResponse response);

    void Failure(BaseResponse baseResponse);
}