package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class DiagnoseRequest {
    @SerializedName("author")
    var author: Int = 1
    @SerializedName("meta")
    var meta: Meta = Meta()
    @SerializedName("status")
    var status: String = "publish"
    @SerializedName("title")
    var title: String = "diagnostics"
}