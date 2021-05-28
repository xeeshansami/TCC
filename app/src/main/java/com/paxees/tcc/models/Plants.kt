package com.paxees.tcc.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Plants() :Serializable,Parcelable{
    var plantValue:String?=null

    constructor(parcel: Parcel) : this() {
        plantValue = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(plantValue)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Plants> {
        override fun createFromParcel(parcel: Parcel): Plants {
            return Plants(parcel)
        }

        override fun newArray(size: Int): Array<Plants?> {
            return arrayOfNulls(size)
        }
    }
}