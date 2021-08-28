package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class StrainRequestTokenResponse {
    @SerializedName("access_token")
    var accessToken: String = ""
}