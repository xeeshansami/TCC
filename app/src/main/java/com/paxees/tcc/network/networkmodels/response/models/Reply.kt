package com.paxees.tcc.network.networkmodels.response.models


import com.google.gson.annotations.SerializedName

class Reply {
    @SerializedName("embeddable")
    var embeddable: Boolean = false
    @SerializedName("href")
    var href: String = ""
}