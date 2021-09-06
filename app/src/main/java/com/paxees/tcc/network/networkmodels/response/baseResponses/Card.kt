package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class Card {
    @SerializedName("address_city")
    var addressCity: Any? = Any()
    @SerializedName("address_country")
    var addressCountry: Any? = Any()
    @SerializedName("address_line1")
    var addressLine1: Any? = Any()
    @SerializedName("address_line1_check")
    var addressLine1Check: Any? = Any()
    @SerializedName("address_line2")
    var addressLine2: Any? = Any()
    @SerializedName("address_state")
    var addressState: Any? = Any()
    @SerializedName("address_zip")
    var addressZip: Any? = Any()
    @SerializedName("address_zip_check")
    var addressZipCheck: Any? = Any()
    @SerializedName("brand")
    var brand: String = ""
    @SerializedName("country")
    var country: String = ""
    @SerializedName("cvc_check")
    var cvcCheck: String = ""
    @SerializedName("dynamic_last4")
    var dynamicLast4: Any? = Any()
    @SerializedName("exp_month")
    var expMonth: Int = 0
    @SerializedName("exp_year")
    var expYear: Int = 0
    @SerializedName("fingerprint")
    var fingerprint: String = ""
    @SerializedName("funding")
    var funding: String = ""
    @SerializedName("id")
    var id: String = ""
    @SerializedName("last4")
    var last4: String = ""
    @SerializedName("metadata")
    var metadata: MetadataXX = MetadataXX()
    @SerializedName("name")
    var name: Any? = Any()
    @SerializedName("object")
    var objectX: String = ""
    @SerializedName("tokenization_method")
    var tokenizationMethod: Any? = Any()
}