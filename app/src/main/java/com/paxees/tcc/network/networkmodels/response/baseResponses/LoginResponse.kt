package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("token")
    var token: String = ""
    @SerializedName("user_display_name")
    var userDisplayName: String = ""
    @SerializedName("user_email")
    var userEmail: String = ""
    @SerializedName("user_nicename")
    var userNicename: String = ""
    @SerializedName("message")
    var message: String = ""
}