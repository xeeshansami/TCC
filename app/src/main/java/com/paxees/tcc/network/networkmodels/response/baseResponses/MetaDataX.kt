package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class MetaDataX {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("key")
    var key: String = ""
    @SerializedName("value")
    var value: String = ""
}