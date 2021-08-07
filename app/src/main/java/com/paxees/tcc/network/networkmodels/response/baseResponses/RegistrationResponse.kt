package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class RegistrationResponse {
    @SerializedName("avatar_url")
    var avatarUrl: String = ""
    @SerializedName("billing")
    var billing: BillingX = BillingX()
    @SerializedName("date_created")
    var dateCreated: String = ""
    @SerializedName("date_created_gmt")
    var dateCreatedGmt: String = ""
    @SerializedName("date_modified")
    var dateModified: String = ""
    @SerializedName("date_modified_gmt")
    var dateModifiedGmt: String = ""
    @SerializedName("email")
    var email: String = ""
    @SerializedName("first_name")
    var firstName: String = ""
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("is_paying_customer")
    var isPayingCustomer: Boolean = false
    @SerializedName("last_name")
    var lastName: String = ""
    @SerializedName("_links")
    var links: LinksX = LinksX()
    @SerializedName("meta_data")
    var metaData: List<MetaDataX> = listOf()
    @SerializedName("role")
    var role: String = ""
    @SerializedName("shipping")
    var shipping: ShippingX = ShippingX()
    @SerializedName("username")
    var username: String = ""
}