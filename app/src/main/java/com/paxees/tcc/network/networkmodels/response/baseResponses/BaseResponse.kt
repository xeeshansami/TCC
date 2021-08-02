package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class BaseResponse {
    @SerializedName("code")
    var code: String = ""
    @SerializedName("data")
    var `data`: Data = Data()
    @SerializedName("message")
    var message: String = ""
}