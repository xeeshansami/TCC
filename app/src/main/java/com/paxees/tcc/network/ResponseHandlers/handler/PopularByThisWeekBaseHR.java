package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.PopularByThisWeekCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PopularByThisWeekResponse;

import retrofit2.Response;

public class PopularByThisWeekBaseHR extends BaseRH<PopularByThisWeekResponse> {

    PopularByThisWeekCallBack callBack;

    public PopularByThisWeekBaseHR(PopularByThisWeekCallBack loginCallBack) {
        this.callBack = loginCallBack;
    }

    @Override
    protected void onSuccess(Response<PopularByThisWeekResponse> response) {
        callBack.PopularByThisWeekSuccess(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.PopularByThisWeekFailure(response);
    }
}
