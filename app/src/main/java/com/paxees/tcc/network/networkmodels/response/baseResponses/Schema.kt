package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class Schema {
    @SerializedName("@context")
    var context: String = ""
    @SerializedName("@graph")
    var graph: List<Graph> = listOf()
}