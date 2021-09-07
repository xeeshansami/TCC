package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.CreateOrderCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.CreateOrderResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class CreateOrderBaseHR extends BaseRH<CreateOrderResponse> {

    CreateOrderCallBack callBack;

    public CreateOrderBaseHR(CreateOrderCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<CreateOrderResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
