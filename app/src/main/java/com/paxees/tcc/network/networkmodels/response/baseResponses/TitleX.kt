package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class TitleX {
    @SerializedName("default")
    var default: String = ""
    @SerializedName("description")
    var description: String = ""
    @SerializedName("id")
    var id: String = ""
    @SerializedName("label")
    var label: String = ""
    @SerializedName("placeholder")
    var placeholder: String = ""
    @SerializedName("tip")
    var tip: String = ""
    @SerializedName("type")
    var type: String = ""
    @SerializedName("value")
    var value: String = ""
}