package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class SingleLocationDetailsResponseItem {
    @SerializedName("Latitude")
    var latitude: String = ""
    @SerializedName("Location-Address")
    var locationAddress: String = ""
    @SerializedName("Location-id")
    var locationId: String = ""
    @SerializedName("Location-Name")
    var locationName: String = ""
    @SerializedName("Location-Post-id")
    var locationPostId: String = ""
    @SerializedName("Longitude")
    var longitude: String = ""
    @SerializedName("Phon-Number")
    var phonNumber: String = ""
    @SerializedName("Product-List")
    var productList: List<Product> = listOf()
}