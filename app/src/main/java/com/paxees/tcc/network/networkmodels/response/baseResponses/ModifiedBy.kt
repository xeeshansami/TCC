package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class ModifiedBy {
    @SerializedName("id")
    var id: String = ""
    @SerializedName("name")
    var name: String = ""
}