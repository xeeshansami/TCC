package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class StrainRequestFormResponse {
    @SerializedName("data")
    var `data`: List<DataX> = listOf()
}