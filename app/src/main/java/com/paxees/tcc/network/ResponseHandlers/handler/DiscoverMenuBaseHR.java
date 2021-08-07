package com.paxees.tcc.network.ResponseHandlers.handler;

import com.paxees.tcc.network.ResponseHandlers.callbacks.DiscoveryMenuCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.DiscoveryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import retrofit2.Response;

public class DiscoverMenuBaseHR extends BaseRH<DiscoveryResponse> {

    DiscoveryMenuCallBack callBack;

    public DiscoverMenuBaseHR(DiscoveryMenuCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onSuccess(Response<DiscoveryResponse> response) {
        callBack.Success(response.body());
    }

    @Override
    protected void onFailure(BaseResponse response) {
        callBack.Failure(response);
    }
}
