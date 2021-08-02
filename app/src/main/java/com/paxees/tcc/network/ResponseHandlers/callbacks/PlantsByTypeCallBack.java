package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PlantsByTypeResponse;

public interface PlantsByTypeCallBack {
    void Success(PlantsByTypeResponse response);

    void Failure(BaseResponse baseResponse);
}
