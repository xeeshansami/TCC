package com.paxees.tcc.network.networkmodels.response.baseResponses

import android.os.Parcel
import android.os.Parcelable

class LoginResponse() : Parcelable {
    val ID: Int?=null
    val allcaps: Allcaps?=null
    val cap_key: String?=null
    val caps: Caps?=null
    val `data`: Data?=null
    val filter: Any?=null
    val roles: List<String>?=null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginResponse> {
        override fun createFromParcel(parcel: Parcel): LoginResponse {
            return LoginResponse(parcel)
        }

        override fun newArray(size: Int): Array<LoginResponse?> {
            return arrayOfNulls(size)
        }
    }
}