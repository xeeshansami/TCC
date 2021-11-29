package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class ImageX {
    @SerializedName("caption")
    var caption: String = ""
    @SerializedName("contentUrl")
    var contentUrl: String = ""
    @SerializedName("@id")
    var id: String = ""
    @SerializedName("inLanguage")
    var inLanguage: String = ""
    @SerializedName("@type")
    var type: String = ""
    @SerializedName("url")
    var url: String = ""
}