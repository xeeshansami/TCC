package com.paxees.tcc.network.networkmodels.response.baseResponses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class StrainResponseItem() : Parcelable {
    @SerializedName("Product-Image-Ur")
    var Product_Image_Url=""
    @SerializedName("Product-Name")
    var Product_Name=""
    @SerializedName("Product-Url")
    var Product_Url=""
    @SerializedName("Product-id")
    var Product_id=0

    constructor(parcel: Parcel) : this() {
        Product_Image_Url = parcel.readString().toString()
        Product_Name = parcel.readString().toString()
        Product_Url = parcel.readString().toString()
        Product_id = parcel.readInt().toInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Product_Image_Url)
        parcel.writeString(Product_Name)
        parcel.writeString(Product_Url)
        parcel.writeInt(Product_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StrainResponseItem> {
        override fun createFromParcel(parcel: Parcel): StrainResponseItem {
            return StrainResponseItem(parcel)
        }

        override fun newArray(size: Int): Array<StrainResponseItem?> {
            return arrayOfNulls(size)
        }
    }
}