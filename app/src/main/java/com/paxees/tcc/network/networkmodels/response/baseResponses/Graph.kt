package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class Graph {
    @SerializedName("author")
    var author: Author= Author()
    @SerializedName("breadcrumb")
    var breadcrumb: Breadcrumb = Breadcrumb()
    @SerializedName("contentUrl")
    var contentUrl: String = ""
    @SerializedName("dateModified")
    var dateModified: String = ""
    @SerializedName("datePublished")
    var datePublished: String = ""
    @SerializedName("description")
    var description: String = ""
    @SerializedName("height")
    var height: Int = 0
    @SerializedName("@id")
    var id: String = ""
    @SerializedName("image")
    var image: Image= Image()
    @SerializedName("inLanguage")
    var inLanguage: String = ""
    @SerializedName("isPartOf")
    var isPartOf: IsPartOf = IsPartOf()
    @SerializedName("itemListElement")
    var itemListElement: List<ItemElement> = listOf()
    @SerializedName("name")
    var name: String = ""
    @SerializedName("potentialAction")
    var potentialAction: List<PotentialAction> = listOf()
    @SerializedName("primaryImageOfPage")
    var primaryImageOfPage: PrimaryImageOfPage = PrimaryImageOfPage()
    @SerializedName("@type")
    var type: String = ""
    @SerializedName("url")
    var url: String = ""
    @SerializedName("width")
    var width: Int = 0
}