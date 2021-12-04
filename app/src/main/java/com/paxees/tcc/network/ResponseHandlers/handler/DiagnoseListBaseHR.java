package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.BrandByCategoryCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.DiagnoseListCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandDetailResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.DiagnoseListResponse;

import retrofit2.Response;

public class DiagnoseListBaseHR extends BaseRH<DiagnoseListResponse> {

    DiagnoseListCallBack callBack;

    public DiagnoseListBaseHR(DiagnoseListCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<DiagnoseListResponse> response) {
        callBack.DiagnoseListSuccess(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.DiagnoseListFailure(response);
    }
}
