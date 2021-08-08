package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.ForgetPasswordCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.UpdateProfileCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ForgetPasswordResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.UpdateProfileResponse;

import retrofit2.Response;

public class ForgetPasswordBaseHR extends BaseRH<ForgetPasswordResponse> {

    ForgetPasswordCallBack callBack;

    public ForgetPasswordBaseHR(ForgetPasswordCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<ForgetPasswordResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
