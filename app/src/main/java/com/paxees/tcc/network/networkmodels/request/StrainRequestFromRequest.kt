package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class StrainRequestFromRequest {
    @SerializedName("data")
    var data: ArrayList<Data> = ArrayList<Data>()
}