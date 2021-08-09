package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.WishlistShareKeyByUserResponse;

public interface WishlistShareKeyByUserCallBack {
    void Success(WishlistShareKeyByUserResponse response);

    void Failure(BaseResponse baseResponse);
}
