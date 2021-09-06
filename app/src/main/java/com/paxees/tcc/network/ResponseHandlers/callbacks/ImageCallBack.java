package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ImageResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

public interface ImageCallBack {
    void Success(ImageResponse response);

    void Failure(BaseResponse baseResponse);
}
