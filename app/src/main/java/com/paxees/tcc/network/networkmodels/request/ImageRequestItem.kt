package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class ImageRequestItem {
    @SerializedName("key")
    var key: String = ""
    @SerializedName("type")
    var type: String = ""
    @SerializedName("value")
    var value: String = ""
}