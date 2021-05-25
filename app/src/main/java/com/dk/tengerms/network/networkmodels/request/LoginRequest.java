
package com.dk.tengerms.network.networkmodels.request;

import java.io.Serializable;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest implements Serializable, Parcelable
{

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    public final static Creator<LoginRequest> CREATOR = new Creator<LoginRequest>() {


        @SuppressWarnings({
            "unchecked"
        })
        public LoginRequest createFromParcel(android.os.Parcel in) {
            return new LoginRequest(in);
        }

        public LoginRequest[] newArray(int size) {
            return (new LoginRequest[size]);
        }

    }
    ;
    private final static long serialVersionUID = 8756084682662677603L;

    protected LoginRequest(android.os.Parcel in) {
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
    }

    public LoginRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(email);
        dest.writeValue(password);
    }

    public int describeContents() {
        return  0;
    }

}
