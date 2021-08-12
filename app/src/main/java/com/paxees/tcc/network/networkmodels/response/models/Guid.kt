package com.paxees.tcc.network.networkmodels.response.models


import com.google.gson.annotations.SerializedName

class Guid {
    @SerializedName("raw")
    var raw: String = ""
    @SerializedName("rendered")
    var rendered: String = ""
}