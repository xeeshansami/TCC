package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainRequestTokenCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainRequestTokenResponse;

import retrofit2.Response;

public class StrainRequestTokenBaseHR extends BaseRH<StrainRequestTokenResponse> {

    StrainRequestTokenCallBack callBack;

    public StrainRequestTokenBaseHR(StrainRequestTokenCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<StrainRequestTokenResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
