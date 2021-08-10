package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class UpdateCartRequest {
    @SerializedName("cart_item_key")
    var cartItemKey: String = ""
    @SerializedName("quantity")
    var quantity: Int = 0
}