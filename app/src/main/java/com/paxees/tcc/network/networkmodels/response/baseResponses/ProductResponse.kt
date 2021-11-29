package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName
import com.paxees.tcc.network.networkmodels.response.models.Category
import com.paxees.tcc.network.networkmodels.response.models.MetaDataX

class ProductResponse {
    @SerializedName("author")
    var author: Int = 0

    @SerializedName("categories")
    var categories: List<Category> = listOf()

    @SerializedName("comment_status")
    var commentStatus: String = ""

    @SerializedName("content")
    var content: Content = Content()

    @SerializedName("date")
    var date: String = ""

    @SerializedName("price")
    var price: String = ""

    @SerializedName("sale_price")
    var sale_price: String = ""
    @SerializedName("short_description")
    var short_description: String = ""

    @SerializedName("name")
    var name: String = ""

    @SerializedName("date_gmt")
    var dateGmt: String = ""

    @SerializedName("excerpt")
    var excerpt: Excerpt = Excerpt()

    @SerializedName("featured_media")
    var featuredMedia: Int = 0

    @SerializedName("format")
    var format: String = ""

    @SerializedName("guid")
    var guid: Guid = Guid()

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("jetpack_featured_media_url")
    var jetpackFeaturedMediaUrl: String = ""

    @SerializedName("link")
    var link: String = ""

    @SerializedName("_links")
    var links: Links = Links()

    @SerializedName("meta_data")
    var metaData: ArrayList<MetaDataX> = arrayListOf()

    @SerializedName("modified")
    var modified: String = ""

    @SerializedName("modified_gmt")
    var modifiedGmt: String = ""

    @SerializedName("ping_status")
    var pingStatus: String = ""

    @SerializedName("slug")
    var slug: String = ""

    @SerializedName("status")
    var status: String = ""

    @SerializedName("sticky")
    var sticky: Boolean = false

    @SerializedName("tags")
    var tags: List<Int> = listOf()

    @SerializedName("template")
    var template: String = ""

    @SerializedName("title")
    var title: Title = Title()

    @SerializedName("type")
    var type: String = ""

    @SerializedName("yoast_head")
    var yoastHead: String = ""

    @SerializedName("yoast_head_json")
    var yoastHeadJson: YoastHeadJson = YoastHeadJson()
}