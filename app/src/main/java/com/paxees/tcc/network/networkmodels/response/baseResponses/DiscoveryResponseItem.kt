package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class DiscoveryResponseItem {
    @SerializedName("CategoryX-id")
    var categoryId: String = ""
    @SerializedName("Menu-id")
    var menuId: Int = 0
    @SerializedName("Menu-item")
    var menuItem: String = ""
    @SerializedName("Url")
    var url: String = ""
}