package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class GetPaymentMethodListOfConsumerResponse {
    @SerializedName("data")
    var `data`: List<DataXX> = listOf()
    @SerializedName("has_more")
    var hasMore: Boolean = false
    @SerializedName("object")
    var objectX: String = ""
    @SerializedName("url")
    var url: String = ""
}