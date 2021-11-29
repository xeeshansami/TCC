package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class OgImage {
    @SerializedName("alt")
    var alt: String = ""
    @SerializedName("height")
    var height: Int = 0
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("path")
    var path: String = ""
    @SerializedName("pixels")
    var pixels: Int = 0
    @SerializedName("size")
    var size: String = ""
    @SerializedName("type")
    var type: String = ""
    @SerializedName("url")
    var url: String = ""
    @SerializedName("width")
    var width: Int = 0
}