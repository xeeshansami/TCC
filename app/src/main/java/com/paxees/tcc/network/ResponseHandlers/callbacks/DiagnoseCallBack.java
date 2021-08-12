package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;
import com.paxees.tcc.network.networkmodels.response.models.DiagnoseResponse;

public interface DiagnoseCallBack {
    void Success(DiagnoseResponse response);

    void Failure(BaseResponse baseResponse);
}
