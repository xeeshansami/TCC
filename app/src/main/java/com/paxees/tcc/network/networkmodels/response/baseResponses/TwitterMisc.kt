package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class TwitterMisc {
    @SerializedName("Est. reading time")
    var estReadingTime: String = ""
    @SerializedName("Written by")
    var writtenBy: String = ""
}