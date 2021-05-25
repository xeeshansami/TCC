
package com.dk.tengerms.network.networkmodels.request;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BrandByCategoryRequest implements Serializable, Parcelable
{

    @SerializedName("catid")
    @Expose
    private String catid;

    public final static Creator<BrandByCategoryRequest> CREATOR = new Creator<BrandByCategoryRequest>() {


        @SuppressWarnings({
            "unchecked"
        })
        public BrandByCategoryRequest createFromParcel(android.os.Parcel in) {
            return new BrandByCategoryRequest(in);
        }

        public BrandByCategoryRequest[] newArray(int size) {
            return (new BrandByCategoryRequest[size]);
        }

    }
    ;
    private final static long serialVersionUID = -8826999260351766816L;

    protected BrandByCategoryRequest(android.os.Parcel in) {
        this.catid = ((String) in.readValue((int.class.getClassLoader())));
    }

    public BrandByCategoryRequest() {
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }


    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(catid);
    }

    public int describeContents() {
        return  0;
    }

}
