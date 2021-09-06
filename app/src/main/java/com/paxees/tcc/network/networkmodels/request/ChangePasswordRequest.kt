package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class ChangePasswordRequest {
    @SerializedName("password")
    var password: String = ""
}