package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class Product {
    @SerializedName("Product-id")
    var productId: Int = 0
    @SerializedName("Product-Image-Url")
    var productImageUrl: String = ""
    @SerializedName("Product-Name")
    var productName: String = ""
    @SerializedName("Product-Url")
    var productUrl: String = ""
}