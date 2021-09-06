package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class AddConsumerInStripRequest {
    @SerializedName("description")
    var description: String = ""
    @SerializedName("email")
    var email: Int = 0
}