package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.AddNewCreditCardCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddNewCreditCardResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class AddNewCreditCardBaseHR extends BaseRH<AddNewCreditCardResponse> {

    AddNewCreditCardCallBack callBack;

    public AddNewCreditCardBaseHR(AddNewCreditCardCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<AddNewCreditCardResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
