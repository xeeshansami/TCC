package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.PriceSummaryCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PriceSummaryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class PriceSummaryBaseHR extends BaseRH<PriceSummaryResponse> {

    PriceSummaryCallBack callBack;

    public PriceSummaryBaseHR(PriceSummaryCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<PriceSummaryResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
