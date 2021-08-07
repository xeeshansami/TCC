package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class AddToCartRequest {
    @SerializedName("product_id")
    var productId: String = ""
    @SerializedName("quantity")
    var quantity: Int = 0
}