
package com.paxees.tcc.network.networkmodels.request;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BrandDetailsRequest implements Serializable, Parcelable
{

    @SerializedName("brand_id")
    @Expose
    private String brand_id="20";

    public final static Creator<BrandDetailsRequest> CREATOR = new Creator<BrandDetailsRequest>() {


        @SuppressWarnings({
            "unchecked"
        })
        public BrandDetailsRequest createFromParcel(android.os.Parcel in) {
            return new BrandDetailsRequest(in);
        }

        public BrandDetailsRequest[] newArray(int size) {
            return (new BrandDetailsRequest[size]);
        }

    }
    ;
    private final static long serialVersionUID = -8826999260351766816L;

    protected BrandDetailsRequest(android.os.Parcel in) {
        this.brand_id = ((String) in.readValue((int.class.getClassLoader())));
    }

    public BrandDetailsRequest() {
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }


    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(brand_id);
    }

    public int describeContents() {
        return  0;
    }

}
