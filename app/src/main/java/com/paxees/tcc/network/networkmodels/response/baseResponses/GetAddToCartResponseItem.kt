package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class GetAddToCartResponseItem {
    @SerializedName("data")
    var `data`: Data = Data()
    @SerializedName("data_hash")
    var dataHash: String = ""
    @SerializedName("key")
    var key: String = ""
    @SerializedName("line_subtotal")
    var lineSubtotal: Double = 0.0
    @SerializedName("line_subtotal_tax")
    var lineSubtotalTax: Int = 0
    @SerializedName("line_tax")
    var lineTax: Int = 0
    @SerializedName("line_tax_data")
    var lineTaxData: LineTaxData = LineTaxData()
    @SerializedName("line_total")
    var lineTotal: Double = 0.0
    @SerializedName("product_id")
    var productId: Int = 0
    @SerializedName("product_image")
    var productImage: String = ""
    @SerializedName("product_name")
    var productName: String = ""
    @SerializedName("product_price")
    var productPrice: String = ""
    @SerializedName("product_title")
    var productTitle: String = ""
    @SerializedName("quantity")
    var quantity: Int = 0
    @SerializedName("variation")
    var variation: List<Any> = listOf()
    @SerializedName("variation_id")
    var variationId: Int = 0
}