package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.WishlistImageResponse;

public interface WishlistImageCallBack {
    void Success(WishlistImageResponse response);

    void Failure(BaseResponse baseResponse);
}
