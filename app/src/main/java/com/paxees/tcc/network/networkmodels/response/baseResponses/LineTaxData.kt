package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class LineTaxData {
    @SerializedName("subtotal")
    var subtotal: List<Any> = listOf()
    @SerializedName("total")
    var total: List<Any> = listOf()
}