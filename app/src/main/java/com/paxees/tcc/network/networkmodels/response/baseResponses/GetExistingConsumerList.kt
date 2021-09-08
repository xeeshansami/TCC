package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class GetExistingConsumerList {
    @SerializedName("data")
    var `data`: List<DataXXX> = listOf()
    @SerializedName("has_more")
    var hasMore: Boolean = false
    @SerializedName("object")
    var objectX: String = ""
    @SerializedName("url")
    var url: String = ""
}