
package com.paxees.tcc.network.networkmodels.response.models;

import java.io.Serializable;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brand implements Serializable, Parcelable
{

    @SerializedName("brand_id")
    @Expose
    private String brandId;
    @SerializedName("brand_banner")
    @Expose
    private String brandBanner;
    @SerializedName("brand_logo")
    @Expose
    private String brandLogo;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("totaloffers")
    @Expose
    private String totaloffers;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("brand_description")
    @Expose
    private String brandDescription;
    @SerializedName("brand_location")
    @Expose
    private String brandLocation;
    @SerializedName("distance")
    @Expose
    private String distance;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public static Creator<Brand> getCREATOR() {
        return CREATOR;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public final static Creator<Brand> CREATOR = new Creator<Brand>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Brand createFromParcel(android.os.Parcel in) {
            return new Brand(in);
        }

        public Brand[] newArray(int size) {
            return (new Brand[size]);
        }

    }
    ;
    private final static long serialVersionUID = 6867410457071964250L;

    protected Brand(android.os.Parcel in) {
        this.brandId = ((String) in.readValue((String.class.getClassLoader())));
        this.brandBanner = ((String) in.readValue((String.class.getClassLoader())));
        this.brandLogo = ((String) in.readValue((String.class.getClassLoader())));
        this.brandName = ((String) in.readValue((String.class.getClassLoader())));
        this.totaloffers = ((String) in.readValue((String.class.getClassLoader())));
        this.rating = ((String) in.readValue((String.class.getClassLoader())));
        this.brandDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.brandLocation = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Brand() {
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandBanner() {
        return brandBanner;
    }

    public void setBrandBanner(String brandBanner) {
        this.brandBanner = brandBanner;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getTotaloffers() {
        return totaloffers;
    }

    public void setTotaloffers(String totaloffers) {
        this.totaloffers = totaloffers;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    public String getBrandLocation() {
        return brandLocation;
    }

    public void setBrandLocation(String brandLocation) {
        this.brandLocation = brandLocation;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(brandId);
        dest.writeValue(brandBanner);
        dest.writeValue(brandLogo);
        dest.writeValue(brandName);
        dest.writeValue(totaloffers);
        dest.writeValue(rating);
        dest.writeValue(brandDescription);
        dest.writeValue(brandLocation);
    }

    public int describeContents() {
        return  0;
    }

}
