package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandDetailResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.DiagnoseListResponse;

public interface DiagnoseListCallBack {
    void DiagnoseListSuccess(DiagnoseListResponse response);

    void DiagnoseListFailure(BaseResponse baseResponse);
}
