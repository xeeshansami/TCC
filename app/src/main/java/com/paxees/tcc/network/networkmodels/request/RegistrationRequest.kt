package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName
import com.paxees.tcc.network.networkmodels.response.baseResponses.Billing

class RegistrationRequest {
    @SerializedName("billing")
    var billing: Billing = Billing()
    @SerializedName("email")
    var email: String = ""
    @SerializedName("first_name")
    var firstName: String = ""
    @SerializedName("last_name")
    var lastName: String = ""
    @SerializedName("password")
    var password: String = ""
    @SerializedName("username")
    var username: String = ""
}