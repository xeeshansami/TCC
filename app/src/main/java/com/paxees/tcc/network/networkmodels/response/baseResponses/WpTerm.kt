package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class WpTerm {
    @SerializedName("embeddable")
    var embeddable: Boolean = false
    @SerializedName("href")
    var href: String = ""
    @SerializedName("taxonomy")
    var taxonomy: String = ""
}