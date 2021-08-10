package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class Address0 {
    @SerializedName("shipping_address_1")
    var shippingAddress1: String = ""
    @SerializedName("shipping_address_2")
    var shippingAddress2: String = ""
    @SerializedName("shipping_city")
    var shippingCity: String = ""
    @SerializedName("shipping_company")
    var shippingCompany: String = ""
    @SerializedName("shipping_country")
    var shippingCountry: String = ""
    @SerializedName("shipping_email")
    var shippingEmail: String = ""
    @SerializedName("shipping_first_name")
    var shippingFirstName: String = ""
    @SerializedName("shipping_last_name")
    var shippingLastName: String = ""
    @SerializedName("shipping_phone")
    var shippingPhone: String = ""
    @SerializedName("shipping_postcode")
    var shippingPostcode: String = ""
    @SerializedName("shipping_state")
    var shippingState: String = ""
}