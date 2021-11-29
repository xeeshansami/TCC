package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class PredecessorVersion {
    @SerializedName("href")
    var href: String = ""
    @SerializedName("id")
    var id: Int = 0
}