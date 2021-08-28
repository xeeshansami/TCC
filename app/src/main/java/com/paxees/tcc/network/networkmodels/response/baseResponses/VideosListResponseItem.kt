package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class VideosListResponseItem {
    @SerializedName("comment_status")
    var commentStatus: String = ""
    @SerializedName("content")
    var content: Content = Content()
    @SerializedName("date")
    var date: String = ""
    @SerializedName("date_gmt")
    var dateGmt: String = ""
    @SerializedName("excerpt")
    var excerpt: Excerpt = Excerpt()
    @SerializedName("featured_media")
    var featuredMedia: Int = 0
    @SerializedName("guid")
    var guid: Guid = Guid()
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("link")
    var link: String = ""
    @SerializedName("_links")
    var links: LinksX = LinksX()
    @SerializedName("menu_order")
    var menuOrder: Int = 0
    @SerializedName("meta")
    var meta: Meta = Meta()
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
    @SerializedName("template")
    var template: String = ""
    @SerializedName("title")
    var title: Title = Title()
    @SerializedName("type")
    var type: String = ""
    @SerializedName("video_category")
    var videoCategory: List<Int> = listOf()
    @SerializedName("yoast_head")
    var yoastHead: String = ""
}