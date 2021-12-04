package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class LinksXXX {
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
    @SerializedName("wp:attachment")
    var wpAttachment: List<WpAttachment> = listOf()
}