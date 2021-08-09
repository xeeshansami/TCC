package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.AddressListCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;
import com.paxees.tcc.network.networkmodels.response.models.MyAddressesListResponse;

import retrofit2.Response;

public class AddressListBaseHR extends BaseRH<MyAddressesListResponse> {

    AddressListCallBack callBack;

    public AddressListBaseHR(AddressListCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<MyAddressesListResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
