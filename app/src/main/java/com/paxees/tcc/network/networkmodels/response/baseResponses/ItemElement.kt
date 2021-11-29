package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class ItemElement {
    @SerializedName("item")
    var item: String = ""
    @SerializedName("name")
    var name: String = ""
    @SerializedName("position")
    var position: Int = 0
    @SerializedName("@type")
    var type: String = ""
}