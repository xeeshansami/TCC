package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class MetaData {
    @SerializedName("key")
    var key: String = ""
    @SerializedName("value")
    var value: Any? = Any()
}