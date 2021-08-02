package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.PlantsByTypeCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PlantsByTypeResponse;

import retrofit2.Response;

public class PlantsByTypeBaseHR extends BaseRH<PlantsByTypeResponse> {

    PlantsByTypeCallBack callBack;

    public PlantsByTypeBaseHR(PlantsByTypeCallBack loginCallBack) {
        this.callBack = loginCallBack;
    }

    @Override
    protected void onSuccess(Response<PlantsByTypeResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
