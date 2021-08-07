package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class PopularByThisWeekResponseItem {
    @SerializedName("Product-Description")
    var productDescription: String = ""
    @SerializedName("Product-id")
    var productId: Int = 0
    @SerializedName("Product-Image-Url")
    var productImageUrl: String = ""
    @SerializedName("Product-Name")
    var productName: String = ""
    @SerializedName("Product-Url")
    var productUrl: String = ""
    @SerializedName("Rating")
    var rating: String = ""
    @SerializedName("Total-Rating")
    var totalRating: Int = 0
}