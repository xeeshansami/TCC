package com.paxees.tcc.network.networkmodels.response.models


import com.google.gson.annotations.SerializedName

class DiagnoseResponse {
    @SerializedName("comment_status")
    var commentStatus: String = ""
    @SerializedName("content")
    var content: Content = Content()
    @SerializedName("date")
    var date: String = ""
    @SerializedName("date_gmt")
    var dateGmt: String = ""
    @SerializedName("featured_media")
    var featuredMedia: Int = 0
    @SerializedName("generated_slug")
    var generatedSlug: String = ""
    @SerializedName("guid")
    var guid: Guid = Guid()
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("link")
    var link: String = ""
    @SerializedName("_links")
    var links: LinksX = LinksX()
    @SerializedName("meta")
    var meta: Meta = Meta()
    @SerializedName("modified")
    var modified: String = ""
    @SerializedName("modified_gmt")
    var modifiedGmt: String = ""
    @SerializedName("password")
    var password: String = ""
    @SerializedName("permalink_template")
    var permalinkTemplate: String = ""
    @SerializedName("ping_status")
    var pingStatus: String = ""
    @SerializedName("slug")
    var slug: String = ""
    @SerializedName("status")
    var status: String = ""
    @SerializedName("template")
    var template: String = ""
    @SerializedName("title")
    var title: Title = Title()
    @SerializedName("type")
    var type: String = ""
    @SerializedName("yoast_head")
    var yoastHead: String = ""
}