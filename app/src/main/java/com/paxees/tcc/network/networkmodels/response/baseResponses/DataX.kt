package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class DataX {
    @SerializedName("code")
    var code: String = ""
    @SerializedName("details")
    var details: Details = Details()
    @SerializedName("message")
    var message: String = ""
    @SerializedName("status")
    var status: String = ""
}