package com.paxees.tcc.network.ResponseHandlers.callbacks;

import com.paxees.tcc.network.networkmodels.response.baseResponses.AddNewCreditCardResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

public interface AddNewCreditCardCallBack {
    void Success(AddNewCreditCardResponse response);

    void Failure(BaseResponse baseResponse);
}
