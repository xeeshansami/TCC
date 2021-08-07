package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class RegistrationRequest {
    @SerializedName("billing")
    var billing: BillingXX = BillingXX()
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