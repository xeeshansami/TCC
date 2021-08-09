package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.GetWishlistCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetWishlistResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class GetWishlistBaseHR extends BaseRH<GetWishlistResponse> {

    GetWishlistCallBack callBack;

    public GetWishlistBaseHR(GetWishlistCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<GetWishlistResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
