package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class StrainBaseHR extends BaseRH<StrainResponse> {

    StrainCallBack callBack;

    public StrainBaseHR(StrainCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<StrainResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
