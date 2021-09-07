package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class LineItem {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("meta_data")
    var metaData: List<Any> = listOf()
    @SerializedName("name")
    var name: String = ""
    @SerializedName("parent_name")
    var parentName: Any? = Any()
    @SerializedName("price")
    var price: Int = 0
    @SerializedName("product_id")
    var productId: Int = 0
    @SerializedName("quantity")
    var quantity: Int = 0
    @SerializedName("sku")
    var sku: String = ""
    @SerializedName("subtotal")
    var subtotal: String = ""
    @SerializedName("subtotal_tax")
    var subtotalTax: String = ""
    @SerializedName("tax_class")
    var taxClass: String = ""
    @SerializedName("taxes")
    var taxes: List<Any> = listOf()
    @SerializedName("total")
    var total: String = ""
    @SerializedName("total_tax")
    var totalTax: String = ""
    @SerializedName("variation_id")
    var variationId: Int = 0
}