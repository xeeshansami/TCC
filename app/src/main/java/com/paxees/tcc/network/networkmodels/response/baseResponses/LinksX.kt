package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class LinksX {
    @SerializedName("collection")
    var collection: List<CollectionX> = listOf()
    @SerializedName("self")
    var self: List<SelfX> = listOf()
}