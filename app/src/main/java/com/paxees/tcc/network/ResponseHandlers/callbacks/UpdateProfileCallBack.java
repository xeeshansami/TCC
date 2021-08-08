package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.UpdateProfileResponse;

public interface UpdateProfileCallBack {
    void Success(UpdateProfileResponse response);

    void Failure(BaseResponse baseResponse);
}
