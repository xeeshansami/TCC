package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class ForgetPasswordResponse {
    @SerializedName("result")
    var result: Boolean = false
}