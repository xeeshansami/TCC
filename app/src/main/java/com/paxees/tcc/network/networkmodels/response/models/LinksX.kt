package com.paxees.tcc.network.networkmodels.response.models


import com.google.gson.annotations.SerializedName

class LinksX {
    @SerializedName("about")
    var about: List<About> = listOf()
    @SerializedName("collection")
    var collection: List<Collection> = listOf()
    @SerializedName("curies")
    var curies: List<Cury> = listOf()
    @SerializedName("replies")
    var replies: List<Reply> = listOf()
    @SerializedName("self")
    var self: List<Self> = listOf()
    @SerializedName("wp:action-publish")
    var wpActionPublish: List<WpActionPublish> = listOf()
    @SerializedName("wp:action-unfiltered-html")
    var wpActionUnfilteredHtml: List<WpActionUnfilteredHtml> = listOf()
    @SerializedName("wp:attachment")
    var wpAttachment: List<WpAttachment> = listOf()
}