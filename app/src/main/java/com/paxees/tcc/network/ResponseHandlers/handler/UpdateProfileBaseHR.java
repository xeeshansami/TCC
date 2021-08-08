package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.UpdateCartCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.UpdateProfileCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.UpdateProfileResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.UpdateCartResponse;

import retrofit2.Response;

public class UpdateProfileBaseHR extends BaseRH<UpdateProfileResponse> {

    UpdateProfileCallBack callBack;

    public UpdateProfileBaseHR(UpdateProfileCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<UpdateProfileResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
