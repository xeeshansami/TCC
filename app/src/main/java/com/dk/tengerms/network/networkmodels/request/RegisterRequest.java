
package com.dk.tengerms.network.networkmodels.request;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterRequest implements Serializable, Parcelable
{

    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("promo")
    @Expose
    private String promo;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static Creator<RegisterRequest> getCREATOR() {
        return CREATOR;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @SerializedName("gender")
    @Expose
    private String gender;
    public final static Creator<RegisterRequest> CREATOR = new Creator<RegisterRequest>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RegisterRequest createFromParcel(android.os.Parcel in) {
            return new RegisterRequest(in);
        }

        public RegisterRequest[] newArray(int size) {
            return (new RegisterRequest[size]);
        }

    }
    ;
    private final static long serialVersionUID = 8756084682662677603L;

    protected RegisterRequest(android.os.Parcel in) {
        this.number = ((String) in.readValue((String.class.getClassLoader())));
        this.promo = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RegisterRequest() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(number);
        dest.writeValue(promo);
        dest.writeValue(gender);
    }

    public int describeContents() {
        return  0;
    }

}
