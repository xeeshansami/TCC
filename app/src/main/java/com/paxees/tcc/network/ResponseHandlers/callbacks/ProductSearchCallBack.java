package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ProductSearchResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

public interface ProductSearchCallBack {
    void Success(ProductSearchResponse response);

    void Failure(BaseResponse baseResponse);
}
