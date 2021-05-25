
package com.paxees.tcc.network.networkmodels.response.models;

import java.io.Serializable;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coupon implements Serializable, Parcelable
{

    @SerializedName("coupon_id")
    @Expose
    private String couponId;
    @SerializedName("coupon_name")
    @Expose
    private String couponName;
    @SerializedName("coupon_description")
    @Expose
    private String couponDescription;
    @SerializedName("coupon_qty")
    @Expose
    private String couponQty;
    @SerializedName("coupon_serialno")
    @Expose
    private String couponSerialno;
    @SerializedName("coupon_terms")
    @Expose
    private String couponTerms;
    public final static Creator<Coupon> CREATOR = new Creator<Coupon>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Coupon createFromParcel(android.os.Parcel in) {
            return new Coupon(in);
        }

        public Coupon[] newArray(int size) {
            return (new Coupon[size]);
        }

    }
    ;
    private final static long serialVersionUID = -2294591173100578054L;

    protected Coupon(android.os.Parcel in) {
        this.couponId = ((String) in.readValue((String.class.getClassLoader())));
        this.couponName = ((String) in.readValue((String.class.getClassLoader())));
        this.couponDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.couponQty = ((String) in.readValue((String.class.getClassLoader())));
        this.couponSerialno = ((String) in.readValue((String.class.getClassLoader())));
        this.couponTerms = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Coupon() {
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponDescription() {
        return couponDescription;
    }

    public void setCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription;
    }

    public String getCouponQty() {
        return couponQty;
    }

    public void setCouponQty(String couponQty) {
        this.couponQty = couponQty;
    }

    public String getCouponSerialno() {
        return couponSerialno;
    }

    public void setCouponSerialno(String couponSerialno) {
        this.couponSerialno = couponSerialno;
    }

    public String getCouponTerms() {
        return couponTerms;
    }

    public void setCouponTerms(String couponTerms) {
        this.couponTerms = couponTerms;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(couponId);
        dest.writeValue(couponName);
        dest.writeValue(couponDescription);
        dest.writeValue(couponQty);
        dest.writeValue(couponSerialno);
        dest.writeValue(couponTerms);
    }

    public int describeContents() {
        return  0;
    }

}
