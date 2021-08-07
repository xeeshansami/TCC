package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class UpdateCartResponse {
    @SerializedName("message")
    var message: String = ""
    @SerializedName("quantity")
    var quantity: Int = 0
}