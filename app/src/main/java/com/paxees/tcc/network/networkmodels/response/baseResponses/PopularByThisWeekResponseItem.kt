package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class PopularByThisWeekResponseItem {
    @SerializedName("Product-id")
    var productId: Int = 0
    @SerializedName("Product-Name")
    var productName: String = ""
}