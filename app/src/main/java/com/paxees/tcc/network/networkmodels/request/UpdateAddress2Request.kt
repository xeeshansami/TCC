package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class UpdateAddress2Request {
    @SerializedName("billing")
    var billing: Billing = Billing()
    @SerializedName("meta_data")
    var metaData: List<MetaData> = listOf()
}