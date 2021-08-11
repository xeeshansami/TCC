package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.SingleLocationDetailsResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

public interface SingleLocationDetailsCallBack {
    void Success(SingleLocationDetailsResponse response);

    void Failure(BaseResponse baseResponse);
}
