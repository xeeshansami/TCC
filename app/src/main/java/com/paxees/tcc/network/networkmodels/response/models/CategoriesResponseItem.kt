package com.paxees.tcc.network.networkmodels.response.models


import com.google.gson.annotations.SerializedName

class CategoriesResponseItem {
    @SerializedName("count")
    var count: Int = 0
    @SerializedName("description")
    var description: String = ""
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("link")
    var link: String = ""
    @SerializedName("_links")
    var links: Links = Links()
    @SerializedName("meta")
    var meta: MetaX = MetaX()
    @SerializedName("name")
    var name: String = ""
    @SerializedName("parent")
    var parent: Int = 0
    @SerializedName("slug")
    var slug: String = ""
    @SerializedName("taxonomy")
    var taxonomy: String = ""
    @SerializedName("yoast_head")
    var yoastHead: String = ""
}