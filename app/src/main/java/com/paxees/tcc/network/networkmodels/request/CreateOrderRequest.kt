package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName
import com.paxees.tcc.network.networkmodels.base.Billing
import com.paxees.tcc.network.networkmodels.base.Shipping

class CreateOrderRequest {
    @SerializedName("billing")
    var billing: Billing = Billing()
    @SerializedName("line_items")
    var lineItems: ArrayList<LineItem> = ArrayList<LineItem>()
    @SerializedName("meta_data")
    var metaData: ArrayList<MetaData> = ArrayList<MetaData>()
    @SerializedName("payment_method")
    var paymentMethod: String = ""
    @SerializedName("payment_method_title")
    var paymentMethodTitle: String = ""
    @SerializedName("set_paid")
    var setPaid: Boolean = false
    @SerializedName("shipping")
    var shipping: Shipping = Shipping()
    @SerializedName("shipping_lines")
    var shippingLines: ArrayList<ShippingLine> = ArrayList<ShippingLine>()
}