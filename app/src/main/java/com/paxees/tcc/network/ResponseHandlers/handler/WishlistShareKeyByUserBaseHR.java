package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.WishlistShareKeyByUserCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.WishlistShareKeyByUserResponse;

import retrofit2.Response;

public class WishlistShareKeyByUserBaseHR extends BaseRH<WishlistShareKeyByUserResponse> {

    WishlistShareKeyByUserCallBack callBack;

    public WishlistShareKeyByUserBaseHR(WishlistShareKeyByUserCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<WishlistShareKeyByUserResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
