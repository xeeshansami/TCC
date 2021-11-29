package com.paxees.tcc.network.networkmodels.response.models


import com.google.gson.annotations.SerializedName

class Category {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String = ""
    @SerializedName("slug")
    var slug: String = ""
}