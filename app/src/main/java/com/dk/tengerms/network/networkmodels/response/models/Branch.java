
package com.dk.tengerms.network.networkmodels.response.models;

import java.io.Serializable;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Branch implements Serializable, Parcelable
{

    @SerializedName("branch_id")
    @Expose
    private String branchId;
    @SerializedName("branch_name")
    @Expose
    private String branchName;
    @SerializedName("branch_lat")
    @Expose
    private String branchLat;
    @SerializedName("branch_long")
    @Expose
    private String branchLong;
    @SerializedName("branch_locality")
    @Expose
    private String branchLocality;
    @SerializedName("branch_address")
    @Expose
    private String branchAddress;
    public final static Creator<Branch> CREATOR = new Creator<Branch>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Branch createFromParcel(android.os.Parcel in) {
            return new Branch(in);
        }

        public Branch[] newArray(int size) {
            return (new Branch[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1631815889856451822L;

    protected Branch(android.os.Parcel in) {
        this.branchId = ((String) in.readValue((String.class.getClassLoader())));
        this.branchName = ((String) in.readValue((String.class.getClassLoader())));
        this.branchLat = ((String) in.readValue((String.class.getClassLoader())));
        this.branchLong = ((String) in.readValue((String.class.getClassLoader())));
        this.branchLocality = ((String) in.readValue((String.class.getClassLoader())));
        this.branchAddress = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Branch() {
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchLat() {
        return branchLat;
    }

    public void setBranchLat(String branchLat) {
        this.branchLat = branchLat;
    }

    public String getBranchLong() {
        return branchLong;
    }

    public void setBranchLong(String branchLong) {
        this.branchLong = branchLong;
    }

    public String getBranchLocality() {
        return branchLocality;
    }

    public void setBranchLocality(String branchLocality) {
        this.branchLocality = branchLocality;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(branchId);
        dest.writeValue(branchName);
        dest.writeValue(branchLat);
        dest.writeValue(branchLong);
        dest.writeValue(branchLocality);
        dest.writeValue(branchAddress);
    }

    public int describeContents() {
        return  0;
    }

}
