
package com.paxees.tcc.network.networkmodels.response.baseResponses;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Parcelable;

import com.paxees.tcc.network.networkmodels.response.models.Category;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("Categories")
    @Expose
    private ArrayList<Category> categories = null;
    public final static Creator<CategoryResponse> CREATOR = new Creator<CategoryResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CategoryResponse createFromParcel(android.os.Parcel in) {
            return new CategoryResponse(in);
        }

        public CategoryResponse[] newArray(int size) {
            return (new CategoryResponse[size]);
        }

    }
    ;
    private final static long serialVersionUID = 3534169705988224615L;

    protected CategoryResponse(android.os.Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.categories, (Category.class.getClassLoader()));
    }

    public CategoryResponse() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeList(categories);
    }

    public int describeContents() {
        return  0;
    }

}
