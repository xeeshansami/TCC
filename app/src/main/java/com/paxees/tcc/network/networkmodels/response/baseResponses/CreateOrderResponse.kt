package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class CreateOrderResponse {
    @SerializedName("billing")
    var billing: Billing = Billing()
    @SerializedName("cart_hash")
    var cartHash: String = ""
    @SerializedName("cart_tax")
    var cartTax: String = ""
    @SerializedName("coupon_lines")
    var couponLines: List<Any> = listOf()
    @SerializedName("created_via")
    var createdVia: String = ""
    @SerializedName("currency")
    var currency: String = ""
    @SerializedName("currency_symbol")
    var currencySymbol: String = ""
    @SerializedName("customer_id")
    var customerId: Int = 0
    @SerializedName("customer_ip_address")
    var customerIpAddress: String = ""
    @SerializedName("customer_note")
    var customerNote: String = ""
    @SerializedName("customer_user_agent")
    var customerUserAgent: String = ""
    @SerializedName("date_completed")
    var dateCompleted: Any? = Any()
    @SerializedName("date_completed_gmt")
    var dateCompletedGmt: Any? = Any()
    @SerializedName("date_created")
    var dateCreated: String = ""
    @SerializedName("date_created_gmt")
    var dateCreatedGmt: String = ""
    @SerializedName("date_modified")
    var dateModified: String = ""
    @SerializedName("date_modified_gmt")
    var dateModifiedGmt: String = ""
    @SerializedName("date_paid")
    var datePaid: Any? = Any()
    @SerializedName("date_paid_gmt")
    var datePaidGmt: Any? = Any()
    @SerializedName("discount_tax")
    var discountTax: String = ""
    @SerializedName("discount_total")
    var discountTotal: String = ""
    @SerializedName("fee_lines")
    var feeLines: List<Any> = listOf()
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("line_items")
    var lineItems: List<LineItem> = listOf()
    @SerializedName("_links")
    var links: Links = Links()
    @SerializedName("meta_data")
    var metaData: List<MetaDataX> = listOf()
    @SerializedName("number")
    var number: String = ""
    @SerializedName("order_key")
    var orderKey: String = ""
    @SerializedName("parent_id")
    var parentId: Int = 0
    @SerializedName("payment_method")
    var paymentMethod: String = ""
    @SerializedName("payment_method_title")
    var paymentMethodTitle: String = ""
    @SerializedName("prices_include_tax")
    var pricesIncludeTax: Boolean = false
    @SerializedName("refunds")
    var refunds: List<Any> = listOf()
    @SerializedName("shipping")
    var shipping: Shipping = Shipping()
    @SerializedName("shipping_lines")
    var shippingLines: List<ShippingLine> = listOf()
    @SerializedName("shipping_tax")
    var shippingTax: String = ""
    @SerializedName("shipping_total")
    var shippingTotal: String = ""
    @SerializedName("status")
    var status: String = ""
    @SerializedName("tax_lines")
    var taxLines: List<Any> = listOf()
    @SerializedName("total")
    var total: String = ""
    @SerializedName("total_tax")
    var totalTax: String = ""
    @SerializedName("transaction_id")
    var transactionId: String = ""
    @SerializedName("version")
    var version: String = ""
}