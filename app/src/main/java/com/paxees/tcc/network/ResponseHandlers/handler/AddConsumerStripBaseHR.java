package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.AddConsumerStripCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddConsumerStripResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class AddConsumerStripBaseHR extends BaseRH<AddConsumerStripResponse> {

    AddConsumerStripCallBack callBack;

    public AddConsumerStripBaseHR(AddConsumerStripCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<AddConsumerStripResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
