package com.paxees.tcc.network.networkmodels.response.baseResponses


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class DiagnoseListResponseItem() : Parcelable {
    @SerializedName("comment_status")
    var commentStatus: String = ""
    @SerializedName("content")
    var content: Content= Content()
    @SerializedName("date")
    var date: String = ""
    @SerializedName("date_gmt")
    var dateGmt: String = ""
    @SerializedName("featured_media")
    var featuredMedia: Int = 0
    @SerializedName("guid")
    var guid: Guid = Guid()
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("link")
    var link: String = ""
    @SerializedName("_links")
    var links: LinksXXX = LinksXXX()
    @SerializedName("meta")
    var meta: Meta= Meta()
    @SerializedName("modified")
    var modified: String = ""
    @SerializedName("modified_gmt")
    var modifiedGmt: String = ""
    @SerializedName("ping_status")
    var pingStatus: String = ""
    @SerializedName("slug")
    var slug: String = ""
    @SerializedName("status")
    var status: String = ""
    @SerializedName("template")
    var template: String = ""
    @SerializedName("title")
    var title: TitleX = TitleX()
    @SerializedName("type")
    var type: String = ""
    @SerializedName("yoast_head")
    var yoastHead: String = ""
    @SerializedName("yoast_head_json")
    var yoastHeadJson: YoastHeadJsonX = YoastHeadJsonX()

    constructor(parcel: Parcel) : this() {
        commentStatus = parcel.readString().toString()
        date = parcel.readString().toString()
        dateGmt = parcel.readString().toString()
        featuredMedia = parcel.readInt()
        id = parcel.readInt()
        link = parcel.readString().toString()
        modified = parcel.readString().toString()
        modifiedGmt = parcel.readString().toString()
        pingStatus = parcel.readString().toString()
        slug = parcel.readString().toString()
        status = parcel.readString().toString()
        template = parcel.readString().toString()
        type = parcel.readString().toString()
        yoastHead = parcel.readString().toString()
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<DiagnoseListResponseItem> {
        override fun createFromParcel(parcel: Parcel): DiagnoseListResponseItem {
            return DiagnoseListResponseItem(parcel)
        }

        override fun newArray(size: Int): Array<DiagnoseListResponseItem?> {
            return arrayOfNulls(size)
        }
    }
}