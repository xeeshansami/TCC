package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class VersionHistory {
    @SerializedName("count")
    var count: Int = 0
    @SerializedName("href")
    var href: String = ""
}