package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class WishlistShareKeyByUserResponseItem {
    @SerializedName("date_added")
    var dateAdded: String = ""
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("share_key")
    var shareKey: String = ""
    @SerializedName("title")
    var title: String = ""
    @SerializedName("user_id")
    var userId: Int = 0
}