package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class LinksXXX {
    @SerializedName("about")
    var about: List<AboutXX> = listOf()
    @SerializedName("collection")
    var collection: List<Collection> = listOf()
    @SerializedName("curies")
    var curies: List<Cury> = listOf()
    @SerializedName("self")
    var self: List<Self> = listOf()
    @SerializedName("wp:post_type")
    var wpPostType: List<WpPostType> = listOf()
}