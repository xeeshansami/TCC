package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class Address4 {
    @SerializedName("billing_address_1")
    var billingAddress1: String = ""
    @SerializedName("billing_address_2")
    var billingAddress2: String = ""
    @SerializedName("billing_city")
    var billingCity: String = ""
    @SerializedName("billing_company")
    var billingCompany: String = ""
    @SerializedName("billing_country")
    var billingCountry: String = ""
    @SerializedName("billing_email")
    var billingEmail: String = ""
    @SerializedName("billing_first_name")
    var billingFirstName: String = ""
    @SerializedName("billing_last_name")
    var billingLastName: String = ""
    @SerializedName("billing_phone")
    var billingPhone: String = ""
    @SerializedName("billing_postcode")
    var billingPostcode: String = ""
    @SerializedName("billing_state")
    var billingState: String = ""
}