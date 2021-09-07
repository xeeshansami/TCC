package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.GetPaymentMethodConusmerCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetPaymentMethodListOfConsumerResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class GetPaymentMethodConsumerBaseHR extends BaseRH<GetPaymentMethodListOfConsumerResponse> {

    GetPaymentMethodConusmerCallBack callBack;

    public GetPaymentMethodConsumerBaseHR(GetPaymentMethodConusmerCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<GetPaymentMethodListOfConsumerResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
