package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PriceSummaryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

public interface PriceSummaryCallBack {
    void Success(PriceSummaryResponse response);

    void Failure(BaseResponse baseResponse);
}
