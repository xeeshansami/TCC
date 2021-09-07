package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class LineItem {
    @SerializedName("product_id")
    var productId: Int = 0
    @SerializedName("quantity")
    var quantity: Int = 0
}