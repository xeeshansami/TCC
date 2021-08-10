package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class BillingX {
    @SerializedName("address_3")
    var address3: Address3 = Address3()
    @SerializedName("address_4")
    var address4: Address4 = Address4()
}