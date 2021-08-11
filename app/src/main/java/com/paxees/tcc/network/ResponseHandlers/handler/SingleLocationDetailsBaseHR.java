package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.SingleLocationDetailsCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.SingleLocationDetailsResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class SingleLocationDetailsBaseHR extends BaseRH<SingleLocationDetailsResponse> {

    SingleLocationDetailsCallBack callBack;

    public SingleLocationDetailsBaseHR(SingleLocationDetailsCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<SingleLocationDetailsResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
