package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class AddNewCreditCardResponse {
    @SerializedName("card")
    var card: Card = Card()
    @SerializedName("client_ip")
    var clientIp: String = ""
    @SerializedName("created")
    var created: Int = 0
    @SerializedName("id")
    var id: String = ""
    @SerializedName("livemode")
    var livemode: Boolean = false
    @SerializedName("object")
    var objectX: String = ""
    @SerializedName("type")
    var type: String = ""
    @SerializedName("used")
    var used: Boolean = false
}