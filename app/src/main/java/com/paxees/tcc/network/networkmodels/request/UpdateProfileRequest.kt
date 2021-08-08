package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName
import com.paxees.tcc.network.networkmodels.response.baseResponses.Billing

class UpdateProfileRequest {
    @SerializedName("billing")
    var billing: Billing= Billing()
    @SerializedName("email")
    var email: String = ""
    @SerializedName("first_name")
    var firstName: String = ""
}