package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetWishlistResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

public interface GetWishlistCallBack {
    void Success(GetWishlistResponse response);

    void Failure(BaseResponse baseResponse);
}
