package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class AddToWishlistResponseItem {
    @SerializedName("date_added")
    var dateAdded: String = ""
    @SerializedName("in_stock")
    var inStock: Boolean = false
    @SerializedName("item_id")
    var itemId: Int = 0
    @SerializedName("meta")
    var meta: List<Any> = listOf()
    @SerializedName("price")
    var price: String = ""
    @SerializedName("product_id")
    var productId: Int = 0
    @SerializedName("variation_id")
    var variationId: Int = 0
}