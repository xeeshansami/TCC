package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.VideosListResponse;

public interface VideosListCallBack {
    void Success(VideosListResponse response);

    void Failure(BaseResponse baseResponse);
}
