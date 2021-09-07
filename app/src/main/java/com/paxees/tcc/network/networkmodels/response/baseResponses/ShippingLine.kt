package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class ShippingLine {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("instance_id")
    var instanceId: String = ""
    @SerializedName("meta_data")
    var metaData: List<Any> = listOf()
    @SerializedName("method_id")
    var methodId: String = ""
    @SerializedName("method_title")
    var methodTitle: String = ""
    @SerializedName("taxes")
    var taxes: List<Any> = listOf()
    @SerializedName("total")
    var total: String = ""
    @SerializedName("total_tax")
    var totalTax: String = ""
}