package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class Robots {
    @SerializedName("follow")
    var follow: String = ""
    @SerializedName("index")
    var index: String = ""
    @SerializedName("max-image-preview")
    var maxImagePreview: String = ""
    @SerializedName("max-snippet")
    var maxSnippet: String = ""
    @SerializedName("max-video-preview")
    var maxVideoPreview: String = ""
}