package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class Dimensions {
    @SerializedName("height")
    var height: String = ""
    @SerializedName("length")
    var length: String = ""
    @SerializedName("width")
    var width: String = ""
}