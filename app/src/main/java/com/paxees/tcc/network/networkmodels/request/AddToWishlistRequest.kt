package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class AddToWishlistRequest {
    @SerializedName("product_id")
    var productId: Int = 0
}