package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.NightTimeUsageCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.NightTimeUsuageResponse;

import retrofit2.Response;

public class NightTimeResponseBaseHR extends BaseRH<NightTimeUsuageResponse> {

    NightTimeUsageCallBack callBack;

    public NightTimeResponseBaseHR(NightTimeUsageCallBack loginCallBack) {
        this.callBack = loginCallBack;
    }

    @Override
    protected void onSuccess(Response<NightTimeUsuageResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
