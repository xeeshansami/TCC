package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.PaymentMethodListCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PaymentMethodListResponse;

import retrofit2.Response;

public class PaymentMethodListBaseHR extends BaseRH<PaymentMethodListResponse> {

    PaymentMethodListCallBack callBack;

    public PaymentMethodListBaseHR(PaymentMethodListCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<PaymentMethodListResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
