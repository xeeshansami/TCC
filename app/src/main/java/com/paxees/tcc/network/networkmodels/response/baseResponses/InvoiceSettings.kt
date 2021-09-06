package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class InvoiceSettings {
    @SerializedName("custom_fields")
    var customFields: Any? = Any()
    @SerializedName("default_payment_method")
    var defaultPaymentMethod: Any? = Any()
    @SerializedName("footer")
    var footer: Any? = Any()
}