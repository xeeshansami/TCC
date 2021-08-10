package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class Value {
    @SerializedName("billing")
    var billing: BillingX = BillingX()
    @SerializedName("shipping")
    var shipping: ShippingX = ShippingX()
}