package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class GraphX {
    @SerializedName("breadcrumb")
    var breadcrumb: Breadcrumb = Breadcrumb()
    @SerializedName("dateModified")
    var dateModified: String = ""
    @SerializedName("datePublished")
    var datePublished: String = ""
    @SerializedName("description")
    var description: String = ""
    @SerializedName("@id")
    var id: String = ""
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
    @SerializedName("@type")
    var type: String = ""
    @SerializedName("url")
    var url: String = ""
}