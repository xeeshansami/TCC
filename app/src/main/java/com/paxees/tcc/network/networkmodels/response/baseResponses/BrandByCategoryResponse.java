
package com.paxees.tcc.network.networkmodels.response.baseResponses;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Parcelable;

import com.paxees.tcc.network.networkmodels.response.models.Brand;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrandByCategoryResponse implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("brands")
    @Expose
    private ArrayList<Brand> brands = null;
    public final static Creator<BrandByCategoryResponse> CREATOR = new Creator<BrandByCategoryResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public BrandByCategoryResponse createFromParcel(android.os.Parcel in) {
            return new BrandByCategoryResponse(in);
        }

        public BrandByCategoryResponse[] newArray(int size) {
            return (new BrandByCategoryResponse[size]);
        }

    }
    ;
    private final static long serialVersionUID = -7393771412138276080L;

    protected BrandByCategoryResponse(android.os.Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.brands, (Brand.class.getClassLoader()));
    }

    public BrandByCategoryResponse() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ArrayList<Brand> getBrands() {
        return brands;
    }

    public void setBrands(ArrayList<Brand> brands) {
        this.brands = brands;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeList(brands);
    }

    public int describeContents() {
        return  0;
    }

}
