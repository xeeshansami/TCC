package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class ShippingX {
    @SerializedName("address_1")
    var address1: String = ""
    @SerializedName("address_2")
    var address2: String = ""
    @SerializedName("city")
    var city: String = ""
    @SerializedName("company")
    var company: String = ""
    @SerializedName("country")
    var country: String = ""
    @SerializedName("first_name")
    var firstName: String = ""
    @SerializedName("last_name")
    var lastName: String = ""
    @SerializedName("postcode")
    var postcode: String = ""
    @SerializedName("state")
    var state: String = ""
}