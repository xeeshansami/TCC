package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class Settings {
    @SerializedName("title")
    var title: TitleX = TitleX()
    @SerializedName("webhook")
    var webhook: Webhook = Webhook()
}