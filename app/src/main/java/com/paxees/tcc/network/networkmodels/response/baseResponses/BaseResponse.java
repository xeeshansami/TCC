
package com.paxees.tcc.network.networkmodels.response.baseResponses;

import java.io.Serializable;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("msg")
    @Expose
    private String msg;
    public final static Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public BaseResponse createFromParcel(android.os.Parcel in) {
            return new BaseResponse(in);
        }

        public BaseResponse[] newArray(int size) {
            return (new BaseResponse[size]);
        }

    }
    ;
    private final static long serialVersionUID = -7965970633532396496L;

    protected BaseResponse(android.os.Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.msg = ((String) in.readValue((String.class.getClassLoader())));
    }

    public BaseResponse() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(msg);
    }

    public int describeContents() {
        return  0;
    }

}
