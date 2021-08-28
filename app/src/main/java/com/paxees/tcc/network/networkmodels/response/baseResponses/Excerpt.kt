package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class Excerpt {
    @SerializedName("protected")
    var `protected`: Boolean = false
    @SerializedName("rendered")
    var rendered: String = ""
}