package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.AddressListResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.LoginResponse;

public interface AddressListCallBack {
    void Success(AddressListResponse response);

    void  Failure(BaseResponse baseResponse);
}
