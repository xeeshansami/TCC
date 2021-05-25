
package com.paxees.tcc.network.networkmodels.response.baseResponses;

import java.io.Serializable;
import java.util.List;
import android.os.Parcelable;

import com.paxees.tcc.network.networkmodels.response.models.Branddetail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrandDetailResponse implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("Branddetails")
    @Expose
    private List<Branddetail> branddetails = null;
    public final static Creator<BrandDetailResponse> CREATOR = new Creator<BrandDetailResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public BrandDetailResponse createFromParcel(android.os.Parcel in) {
            return new BrandDetailResponse(in);
        }

        public BrandDetailResponse[] newArray(int size) {
            return (new BrandDetailResponse[size]);
        }

    }
    ;
    private final static long serialVersionUID = 531795128551000036L;

    protected BrandDetailResponse(android.os.Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.branddetails, (Branddetail.class.getClassLoader()));
    }

    public BrandDetailResponse() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Branddetail> getBranddetails() {
        return branddetails;
    }

    public void setBranddetails(List<Branddetail> branddetails) {
        this.branddetails = branddetails;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeList(branddetails);
    }

    public int describeContents() {
        return  0;
    }

}
