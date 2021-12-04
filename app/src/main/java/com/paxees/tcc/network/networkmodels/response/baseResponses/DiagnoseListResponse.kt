package com.paxees.tcc.network.networkmodels.response.baseResponses


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class DiagnoseListResponse() : ArrayList<DiagnoseListResponseItem>(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DiagnoseListResponse> {
        override fun createFromParcel(parcel: Parcel): DiagnoseListResponse {
            return DiagnoseListResponse(parcel)
        }

        override fun newArray(size: Int): Array<DiagnoseListResponse?> {
            return arrayOfNulls(size)
        }
    }
}