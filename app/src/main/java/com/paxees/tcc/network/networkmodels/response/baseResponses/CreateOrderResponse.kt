package com.paxees.tcc.network.networkmodels.response.baseResponses


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class CreateOrderResponse() : Parcelable {
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

    constructor(parcel: Parcel) : this() {
        cartHash = parcel.readString().toString()
        cartTax = parcel.readString().toString()
        createdVia = parcel.readString().toString()
        currency = parcel.readString().toString()
        currencySymbol = parcel.readString().toString()
        customerId = parcel.readInt()
        customerIpAddress = parcel.readString().toString()
        customerNote = parcel.readString().toString()
        customerUserAgent = parcel.readString().toString()
        dateCreated = parcel.readString().toString()
        dateCreatedGmt = parcel.readString().toString()
        dateModified = parcel.readString().toString()
        dateModifiedGmt = parcel.readString().toString()
        discountTax = parcel.readString().toString()
        discountTotal = parcel.readString().toString()
        id = parcel.readInt().toInt()
        number = parcel.readString().toString()
        orderKey = parcel.readString().toString()
        parentId = parcel.readInt().toInt()
        paymentMethod = parcel.readString().toString()
        paymentMethodTitle = parcel.readString().toString()
        pricesIncludeTax = parcel.readByte() != 0.toByte()
        shippingTax = parcel.readString().toString()
        shippingTotal = parcel.readString().toString()
        status = parcel.readString().toString()
        total = parcel.readString().toString()
        totalTax = parcel.readString().toString()
        transactionId = parcel.readString().toString()
        version = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cartHash)
        parcel.writeString(cartTax)
        parcel.writeString(createdVia)
        parcel.writeString(currency)
        parcel.writeString(currencySymbol)
        parcel.writeInt(customerId)
        parcel.writeString(customerIpAddress)
        parcel.writeString(customerNote)
        parcel.writeString(customerUserAgent)
        parcel.writeString(dateCreated)
        parcel.writeString(dateCreatedGmt)
        parcel.writeString(dateModified)
        parcel.writeString(dateModifiedGmt)
        parcel.writeString(discountTax)
        parcel.writeString(discountTotal)
        parcel.writeInt(id)
        parcel.writeString(number)
        parcel.writeString(orderKey)
        parcel.writeInt(parentId)
        parcel.writeString(paymentMethod)
        parcel.writeString(paymentMethodTitle)
        parcel.writeByte(if (pricesIncludeTax) 1 else 0)
        parcel.writeString(shippingTax)
        parcel.writeString(shippingTotal)
        parcel.writeString(status)
        parcel.writeString(total)
        parcel.writeString(totalTax)
        parcel.writeString(transactionId)
        parcel.writeString(version)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CreateOrderResponse> {
        override fun createFromParcel(parcel: Parcel): CreateOrderResponse {
            return CreateOrderResponse(parcel)
        }

        override fun newArray(size: Int): Array<CreateOrderResponse?> {
            return arrayOfNulls(size)
        }
    }
}