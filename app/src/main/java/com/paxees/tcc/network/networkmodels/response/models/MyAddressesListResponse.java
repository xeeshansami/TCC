
package com.paxees.tcc.network.networkmodels.response.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyAddressesListResponse implements Serializable
{

    @SerializedName("billing")
    @Expose
    private List<Billing> billing = null;
    @SerializedName("shipping")
    @Expose
    private List<Shipping> shipping = null;
    private final static long serialVersionUID = 1365546103307950070L;

    public List<Billing> getBilling() {
        return billing;
    }

    public void setBilling(List<Billing> billing) {
        this.billing = billing;
    }

    public List<Shipping> getShipping() {
        return shipping;
    }

    public void setShipping(List<Shipping> shipping) {
        this.shipping = shipping;
    }

}
