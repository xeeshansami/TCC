package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class Subscriptions {
    @SerializedName("data")
    var `data`: List<Any> = listOf()
    @SerializedName("has_more")
    var hasMore: Boolean = false
    @SerializedName("object")
    var objectX: String = ""
    @SerializedName("total_count")
    var totalCount: Int = 0
    @SerializedName("url")
    var url: String = ""
}