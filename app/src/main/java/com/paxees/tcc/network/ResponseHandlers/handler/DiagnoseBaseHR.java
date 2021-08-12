package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.DiagnoseCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;
import com.paxees.tcc.network.networkmodels.response.models.DiagnoseResponse;

import retrofit2.Response;

public class DiagnoseBaseHR extends BaseRH<DiagnoseResponse> {

    DiagnoseCallBack callBack;

    public DiagnoseBaseHR(DiagnoseCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<DiagnoseResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
