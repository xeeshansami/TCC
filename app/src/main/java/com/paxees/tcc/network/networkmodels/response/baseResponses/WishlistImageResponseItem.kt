package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class WishlistImageResponseItem {
    @SerializedName("Product-id")
    var productId: String = ""
    @SerializedName("Product-image")
    var productImage: String = ""
    @SerializedName("product-title")
    var productTitle: String = ""
}