package com.paxees.tcc.network.networkmodels.response.models


import com.google.gson.annotations.SerializedName

class CuryX {
    @SerializedName("href")
    var href: String = ""
    @SerializedName("name")
    var name: String = ""
    @SerializedName("templated")
    var templated: Boolean = false
}