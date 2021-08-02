package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.CustomerDetailsCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.CustomerDetailsResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class CustomerDetailsBaseHR extends BaseRH<CustomerDetailsResponse> {

    CustomerDetailsCallBack callBack;

    public CustomerDetailsBaseHR(CustomerDetailsCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<CustomerDetailsResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
