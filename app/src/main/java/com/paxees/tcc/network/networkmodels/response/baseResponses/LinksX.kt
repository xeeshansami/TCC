package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class LinksX {
    @SerializedName("about")
    var about: List<AboutX> = listOf()
    @SerializedName("collection")
    var collection: List<Collection> = listOf()
    @SerializedName("curies")
    var curies: List<Cury> = listOf()
    @SerializedName("replies")
    var replies: List<Reply> = listOf()
    @SerializedName("self")
    var self: List<Self> = listOf()
    @SerializedName("wp:attachment")
    var wpAttachment: List<WpAttachment> = listOf()
    @SerializedName("wp:featuredmedia")
    var wpFeaturedmedia: List<WpFeaturedmedia> = listOf()
    @SerializedName("wp:term")
    var wpTerm: List<WpTerm> = listOf()
}