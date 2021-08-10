package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.models.MyAddressesListResponse;

public interface AddressListCallBack {
    void Success(MyAddressesListResponse response);

    void  Failure(BaseResponse baseResponse);
}
