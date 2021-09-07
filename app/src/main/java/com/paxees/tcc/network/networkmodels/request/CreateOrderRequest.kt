package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class CreateOrderRequest {
    @SerializedName("billing")
    var billing: Billing = Billing()
    @SerializedName("line_items")
    var lineItems: List<LineItem> = listOf()
    @SerializedName("meta_data")
    var metaData: List<MetaData> = listOf()
    @SerializedName("payment_method")
    var paymentMethod: String = ""
    @SerializedName("payment_method_title")
    var paymentMethodTitle: String = ""
    @SerializedName("set_paid")
    var setPaid: Boolean = false
    @SerializedName("shipping")
    var shipping: Shipping = Shipping()
    @SerializedName("shipping_lines")
    var shippingLines: List<ShippingLine> = listOf()
}