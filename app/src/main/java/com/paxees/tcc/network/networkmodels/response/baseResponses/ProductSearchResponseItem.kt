package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class ProductSearchResponseItem {
    @SerializedName("attributes")
    var attributes: List<Any> = listOf()
    @SerializedName("average_rating")
    var averageRating: String = ""
    @SerializedName("backordered")
    var backordered: Boolean = false
    @SerializedName("backorders")
    var backorders: String = ""
    @SerializedName("backorders_allowed")
    var backordersAllowed: Boolean = false
    @SerializedName("button_text")
    var buttonText: String = ""
    @SerializedName("catalog_visibility")
    var catalogVisibility: String = ""
    @SerializedName("categories")
    var categories: List<Category> = listOf()
    @SerializedName("cross_sell_ids")
    var crossSellIds: List<Any> = listOf()
    @SerializedName("date_created")
    var dateCreated: String = ""
    @SerializedName("date_created_gmt")
    var dateCreatedGmt: String = ""
    @SerializedName("date_modified")
    var dateModified: String = ""
    @SerializedName("date_modified_gmt")
    var dateModifiedGmt: String = ""
    @SerializedName("date_on_sale_from")
    var dateOnSaleFrom: Any? = Any()
    @SerializedName("date_on_sale_from_gmt")
    var dateOnSaleFromGmt: Any? = Any()
    @SerializedName("date_on_sale_to")
    var dateOnSaleTo: Any? = Any()
    @SerializedName("date_on_sale_to_gmt")
    var dateOnSaleToGmt: Any? = Any()
    @SerializedName("default_attributes")
    var defaultAttributes: List<Any> = listOf()
    @SerializedName("description")
    var description: String = ""
    @SerializedName("dimensions")
    var dimensions: Dimensions = Dimensions()
    @SerializedName("download_expiry")
    var downloadExpiry: Int = 0
    @SerializedName("download_limit")
    var downloadLimit: Int = 0
    @SerializedName("downloadable")
    var downloadable: Boolean = false
    @SerializedName("downloads")
    var downloads: List<Any> = listOf()
    @SerializedName("external_url")
    var externalUrl: String = ""
    @SerializedName("featured")
    var featured: Boolean = false
    @SerializedName("grouped_products")
    var groupedProducts: List<Any> = listOf()
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("images")
    var images: List<Image> = listOf()
    @SerializedName("_links")
    var links: Links = Links()
    @SerializedName("manage_stock")
    var manageStock: Boolean = false
    @SerializedName("menu_order")
    var menuOrder: Int = 0
    @SerializedName("meta_data")
    var metaData: List<MetaData> = listOf()
    @SerializedName("name")
    var name: String = ""
    @SerializedName("on_sale")
    var onSale: Boolean = false
    @SerializedName("parent_id")
    var parentId: Int = 0
    @SerializedName("permalink")
    var permalink: String = ""
    @SerializedName("price")
    var price: String = ""
    @SerializedName("price_html")
    var priceHtml: String = ""
    @SerializedName("purchasable")
    var purchasable: Boolean = false
    @SerializedName("purchase_note")
    var purchaseNote: String = ""
    @SerializedName("rating_count")
    var ratingCount: Int = 0
    @SerializedName("regular_price")
    var regularPrice: String = ""
    @SerializedName("related_ids")
    var relatedIds: List<Int> = listOf()
    @SerializedName("reviews_allowed")
    var reviewsAllowed: Boolean = false
    @SerializedName("sale_price")
    var salePrice: String = ""
    @SerializedName("shipping_class")
    var shippingClass: String = ""
    @SerializedName("shipping_class_id")
    var shippingClassId: Int = 0
    @SerializedName("shipping_required")
    var shippingRequired: Boolean = false
    @SerializedName("shipping_taxable")
    var shippingTaxable: Boolean = false
    @SerializedName("short_description")
    var shortDescription: String = ""
    @SerializedName("sku")
    var sku: String = ""
    @SerializedName("slug")
    var slug: String = ""
    @SerializedName("sold_individually")
    var soldIndividually: Boolean = false
    @SerializedName("status")
    var status: String = ""
    @SerializedName("stock_quantity")
    var stockQuantity: Int? = 0
    @SerializedName("stock_status")
    var stockStatus: String = ""
    @SerializedName("tags")
    var tags: List<Any> = listOf()
    @SerializedName("tax_class")
    var taxClass: String = ""
    @SerializedName("tax_status")
    var taxStatus: String = ""
    @SerializedName("total_sales")
    var totalSales: Int = 0
    @SerializedName("type")
    var type: String = ""
    @SerializedName("upsell_ids")
    var upsellIds: List<Any> = listOf()
    @SerializedName("variations")
    var variations: List<Any> = listOf()
    @SerializedName("virtual")
    var virtual: Boolean = false
    @SerializedName("weight")
    var weight: String = ""
    @SerializedName("yoast_head")
    var yoastHead: String = ""
}