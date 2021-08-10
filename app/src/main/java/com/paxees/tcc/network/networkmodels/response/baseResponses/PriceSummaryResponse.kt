package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class PriceSummaryResponse {
    @SerializedName("cart_contents_tax")
    var cartContentsTax: String = ""
    @SerializedName("cart_contents_taxes")
    var cartContentsTaxes: List<Any> = listOf()
    @SerializedName("cart_contents_total")
    var cartContentsTotal: String = ""
    @SerializedName("discount_tax")
    var discountTax: String = ""
    @SerializedName("discount_total")
    var discountTotal: String = ""
    @SerializedName("fee_tax")
    var feeTax: String = ""
    @SerializedName("fee_taxes")
    var feeTaxes: List<Any> = listOf()
    @SerializedName("fee_total")
    var feeTotal: String = ""
    @SerializedName("shipping_tax")
    var shippingTax: String = ""
    @SerializedName("shipping_taxes")
    var shippingTaxes: List<Any> = listOf()
    @SerializedName("shipping_total")
    var shippingTotal: String = ""
    @SerializedName("subtotal")
    var subtotal: String = ""
    @SerializedName("subtotal_tax")
    var subtotalTax: String = ""
    @SerializedName("total")
    var total: String = ""
    @SerializedName("total_tax")
    var totalTax: String = ""
}