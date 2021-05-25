
package com.paxees.tcc.network.networkmodels.request;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DashboardRequest implements Serializable, Parcelable
{

    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String longi;
    public final static Creator<DashboardRequest> CREATOR = new Creator<DashboardRequest>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DashboardRequest createFromParcel(android.os.Parcel in) {
            return new DashboardRequest(in);
        }

        public DashboardRequest[] newArray(int size) {
            return (new DashboardRequest[size]);
        }

    }
    ;
    private final static long serialVersionUID = 8756084682662677603L;

    protected DashboardRequest(android.os.Parcel in) {
        this.lat = ((String) in.readValue((String.class.getClassLoader())));
        this.longi = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DashboardRequest() {
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(lat);
        dest.writeValue(longi);
    }

    public int describeContents() {
        return  0;
    }

}
