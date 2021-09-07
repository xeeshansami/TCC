package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class ShippingLine {
    @SerializedName("method_id")
    var methodId: String = ""
    @SerializedName("method_title")
    var methodTitle: String = ""
    @SerializedName("total")
    var total: String = ""
}