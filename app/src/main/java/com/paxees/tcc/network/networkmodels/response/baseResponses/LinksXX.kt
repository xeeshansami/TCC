package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class LinksXX {
    @SerializedName("about")
    var about: List<About> = listOf()
    @SerializedName("author")
    var author: List<Author> = listOf()
    @SerializedName("collection")
    var collection: List<Collection> = listOf()
    @SerializedName("curies")
    var curies: List<Cury> = listOf()
    @SerializedName("predecessor-version")
    var predecessorVersion: List<PredecessorVersion> = listOf()
    @SerializedName("replies")
    var replies: List<Reply> = listOf()
    @SerializedName("self")
    var self: List<Self> = listOf()
    @SerializedName("version-history")
    var versionHistory: List<VersionHistory> = listOf()
    @SerializedName("wp:attachment")
    var wpAttachment: List<WpAttachmentX> = listOf()
    @SerializedName("wp:featuredmedia")
    var wpFeaturedmedia: List<WpFeaturedmediaX> = listOf()
    @SerializedName("wp:term")
    var wpTerm: List<WpTerm> = listOf()
}