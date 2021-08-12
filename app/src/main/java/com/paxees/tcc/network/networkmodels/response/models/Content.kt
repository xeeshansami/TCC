package com.paxees.tcc.network.networkmodels.response.models


import com.google.gson.annotations.SerializedName

class Content {
    @SerializedName("block_version")
    var blockVersion: Int = 0
    @SerializedName("protected")
    var `protected`: Boolean = false
    @SerializedName("raw")
    var raw: String = ""
    @SerializedName("rendered")
    var rendered: String = ""
}