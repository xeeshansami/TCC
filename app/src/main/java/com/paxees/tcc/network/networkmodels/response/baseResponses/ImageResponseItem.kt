package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class ImageResponseItem {
    @SerializedName("Image-id")
    var imageId: String = ""
    @SerializedName("Image-url")
    var imageUrl: String = ""
}