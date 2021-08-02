package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PopularByThisWeekResponse;

public interface PopularByThisWeekCallBack {
    void PopularByThisWeekSuccess(PopularByThisWeekResponse response);

    void PopularByThisWeekFailure(BaseResponse baseResponse);
}
