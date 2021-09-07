package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class AddConsumerStripResponse {
    @SerializedName("address")
    var address: Any? = Any()
    @SerializedName("balance")
    var balance: Int = 0
    @SerializedName("created")
    var created: Int = 0
    @SerializedName("currency")
    var currency: Any? = Any()
    @SerializedName("default_source")
    var defaultSource: Any? = Any()
    @SerializedName("delinquent")
    var delinquent: Boolean = false
    @SerializedName("description")
    var description: String = ""
    @SerializedName("discount")
    var discount: Any? = Any()
    @SerializedName("email")
    var email: String = ""
    @SerializedName("id")
    var id: String = ""
    @SerializedName("invoice_prefix")
    var invoicePrefix: String = ""
    @SerializedName("invoice_settings")
    var invoiceSettings: InvoiceSettings = InvoiceSettings()
    @SerializedName("livemode")
    var livemode: Boolean = false
    @SerializedName("metadata")
    var metadata: MetaData = MetaData()
    @SerializedName("name")
    var name: Any? = Any()
    @SerializedName("next_invoice_sequence")
    var nextInvoiceSequence: Int = 0
    @SerializedName("object")
    var objectX: String = ""
    @SerializedName("phone")
    var phone: Any? = Any()
    @SerializedName("preferred_locales")
    var preferredLocales: List<Any> = listOf()
    @SerializedName("shipping")
    var shipping: Any? = Any()
    @SerializedName("sources")
    var sources: Sources = Sources()
    @SerializedName("subscriptions")
    var subscriptions: Subscriptions = Subscriptions()
    @SerializedName("tax_exempt")
    var taxExempt: String = ""
    @SerializedName("tax_ids")
    var taxIds: TaxIds = TaxIds()
}