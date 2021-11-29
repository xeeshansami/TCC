package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class PotentialAction {
    @SerializedName("query-input")
    var queryInput: String = ""
    @SerializedName("target")
    var target: Any? = Any()
    @SerializedName("@type")
    var type: String = ""
}