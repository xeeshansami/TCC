package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class YoastHeadJson {
    @SerializedName("article_published_time")
    var articlePublishedTime: String = ""
    @SerializedName("canonical")
    var canonical: String = ""
    @SerializedName("description")
    var description: String = ""
    @SerializedName("og_description")
    var ogDescription: String = ""
    @SerializedName("og_image")
    var ogImage: List<OgImage> = listOf()
    @SerializedName("og_locale")
    var ogLocale: String = ""
    @SerializedName("og_site_name")
    var ogSiteName: String = ""
    @SerializedName("og_title")
    var ogTitle: String = ""
    @SerializedName("og_type")
    var ogType: String = ""
    @SerializedName("og_url")
    var ogUrl: String = ""
    @SerializedName("robots")
    var robots: Robots = Robots()
    @SerializedName("schema")
    var schema: Schema = Schema()
    @SerializedName("title")
    var title: String = ""
    @SerializedName("twitter_card")
    var twitterCard: String = ""
    @SerializedName("twitter_misc")
    var twitterMisc: TwitterMisc = TwitterMisc()
}