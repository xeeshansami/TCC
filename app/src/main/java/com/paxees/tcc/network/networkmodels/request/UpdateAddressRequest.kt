package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName
import com.paxees.tcc.network.networkmodels.base.Shipping

class UpdateAddressRequest {
    @SerializedName("shipping")
    var shipping: Shipping = Shipping()
    @SerializedName("meta_data")
    var metaData: List<MetaData> = listOf()
}