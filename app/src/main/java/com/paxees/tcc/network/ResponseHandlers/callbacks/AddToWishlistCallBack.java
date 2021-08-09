package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.AddToWishlistResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

public interface AddToWishlistCallBack {
    void Success(AddToWishlistResponse response);

    void Failure(BaseResponse baseResponse);
}
