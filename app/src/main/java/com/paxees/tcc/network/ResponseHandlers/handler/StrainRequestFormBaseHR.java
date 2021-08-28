package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainRequestFormCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainRequestFormResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class StrainRequestFormBaseHR extends BaseRH<StrainRequestFormResponse> {

    StrainRequestFormCallBack callBack;

    public StrainRequestFormBaseHR(StrainRequestFormCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<StrainRequestFormResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
