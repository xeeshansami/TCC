package com.paxees.tcc.network.networkmodels.response.baseResponses


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PlantsByTypeResponseItem() : Parcelable {
    @SerializedName("Category-id")
    var categoryId: Int = 0
    @SerializedName("Category-Image-Url")
    var categoryImageUrl: String = ""
    @SerializedName("Category-Name")
    var categoryName: String = ""
    @SerializedName("Total-Product")
    var totalProduct: Int = 0

    constructor(parcel: Parcel) : this() {
        categoryId = parcel.readInt()
        categoryImageUrl = parcel.readString().toString()
        categoryName = parcel.readString().toString()
        totalProduct = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(categoryId)
        parcel.writeString(categoryImageUrl)
        parcel.writeString(categoryName)
        parcel.writeInt(totalProduct)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlantsByTypeResponseItem> {
        override fun createFromParcel(parcel: Parcel): PlantsByTypeResponseItem {
            return PlantsByTypeResponseItem(parcel)
        }

        override fun newArray(size: Int): Array<PlantsByTypeResponseItem?> {
            return arrayOfNulls(size)
        }
    }
}