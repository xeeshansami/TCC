package com.paxees.tcc.network.networkmodels.response.models


import com.google.gson.annotations.SerializedName

class Links {
    @SerializedName("collection")
    var collection: List<Collection> = listOf()
    @SerializedName("self")
    var self: List<Self> = listOf()
}