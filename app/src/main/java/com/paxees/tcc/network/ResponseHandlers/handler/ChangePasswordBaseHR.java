

package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.ChangePasswordCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ChangePasswordResponse;

import retrofit2.Response;

public class ChangePasswordBaseHR extends BaseRH<ChangePasswordResponse> {

    ChangePasswordCallBack callBack;

    public ChangePasswordBaseHR(ChangePasswordCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<ChangePasswordResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
