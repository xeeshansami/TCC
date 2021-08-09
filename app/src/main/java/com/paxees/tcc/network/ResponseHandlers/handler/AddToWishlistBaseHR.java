package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.AddToWishlistCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddToWishlistResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class AddToWishlistBaseHR extends BaseRH<AddToWishlistResponse> {

    AddToWishlistCallBack callBack;

    public AddToWishlistBaseHR(AddToWishlistCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<AddToWishlistResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
