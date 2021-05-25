
package com.dk.tengerms.network.networkmodels.response.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Branddetail implements Serializable, Parcelable
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
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("brand_description")
    @Expose
    private String brandDescription;
    @SerializedName("brand_location")
    @Expose
    private String brandLocation;
    @SerializedName("branches")
    @Expose
    private ArrayList<Branch> branches = null;
    @SerializedName("coupons")
    @Expose
    private ArrayList<Coupon> coupons = null;
    public final static Creator<Branddetail> CREATOR = new Creator<Branddetail>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Branddetail createFromParcel(android.os.Parcel in) {
            return new Branddetail(in);
        }

        public Branddetail[] newArray(int size) {
            return (new Branddetail[size]);
        }

    }
    ;
    private final static long serialVersionUID = 4404309783356796537L;

    protected Branddetail(android.os.Parcel in) {
        this.brandId = ((String) in.readValue((String.class.getClassLoader())));
        this.brandBanner = ((String) in.readValue((String.class.getClassLoader())));
        this.brandLogo = ((String) in.readValue((String.class.getClassLoader())));
        this.brandName = ((String) in.readValue((String.class.getClassLoader())));
        this.totaloffers = ((String) in.readValue((String.class.getClassLoader())));
        this.distance = ((String) in.readValue((String.class.getClassLoader())));
        this.rating = ((String) in.readValue((String.class.getClassLoader())));
        this.brandDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.brandLocation = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.branches, (Object.class.getClassLoader()));
        in.readList(this.coupons, (Coupon.class.getClassLoader()));
    }

    public Branddetail() {
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
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

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<Branch> branches) {
        this.branches = branches;
    }

    public ArrayList<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(ArrayList<Coupon> coupons) {
        this.coupons = coupons;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(brandId);
        dest.writeValue(brandBanner);
        dest.writeValue(brandLogo);
        dest.writeValue(brandName);
        dest.writeValue(totaloffers);
        dest.writeValue(distance);
        dest.writeValue(rating);
        dest.writeValue(brandDescription);
        dest.writeValue(brandLocation);
        dest.writeList(branches);
        dest.writeList(coupons);
    }

    public int describeContents() {
        return  0;
    }

}
