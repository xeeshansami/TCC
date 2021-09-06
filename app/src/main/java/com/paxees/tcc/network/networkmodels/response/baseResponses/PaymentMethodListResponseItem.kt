package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class PaymentMethodListResponseItem {
    @SerializedName("description")
    var description: String = ""
    @SerializedName("enabled")
    var enabled: Boolean = false
    @SerializedName("id")
    var id: String = ""
    @SerializedName("_links")
    var links: Links = Links()
    @SerializedName("method_description")
    var methodDescription: String = ""
    @SerializedName("method_supports")
    var methodSupports: List<String> = listOf()
    @SerializedName("method_title")
    var methodTitle: String = ""
    @SerializedName("order")
    var order: String = ""
    @SerializedName("settings")
    var settings: Settings = Settings()
    @SerializedName("title")
    var title: String = ""
}