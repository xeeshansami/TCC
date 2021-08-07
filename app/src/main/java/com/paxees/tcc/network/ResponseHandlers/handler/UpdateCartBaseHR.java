package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.UpdateCartCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.UpdateCartResponse;

import retrofit2.Response;

public class UpdateCartBaseHR extends BaseRH<UpdateCartResponse> {

    UpdateCartCallBack callBack;

    public UpdateCartBaseHR(UpdateCartCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<UpdateCartResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
