
package com.paxees.tcc.network.networkmodels.response.models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Billing implements Serializable
{

    @SerializedName("billing_first_name")
    @Expose
    private String billingFirstName;
    @SerializedName("billing_last_name")
    @Expose
    private String billingLastName;
    @SerializedName("billing_company")
    @Expose
    private String billingCompany;
    @SerializedName("billing_country")
    @Expose
    private String billingCountry;
    @SerializedName("billing_address_1")
    @Expose
    private String billingAddress1;
    @SerializedName("billing_address_2")
    @Expose
    private String billingAddress2;
    @SerializedName("billing_city")
    @Expose
    private String billingCity;
    @SerializedName("billing_state")
    @Expose
    private String billingState;
    @SerializedName("billing_postcode")
    @Expose
    private String billingPostcode;
    @SerializedName("billing_phone")
    @Expose
    private String billingPhone;
    @SerializedName("billing_email")
    @Expose
    private String billingEmail;
    private final static long serialVersionUID = -5583654481718495809L;

    public String getBillingFirstName() {
        return billingFirstName;
    }

    public void setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
    }

    public String getBillingLastName() {
        return billingLastName;
    }

    public void setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
    }

    public String getBillingCompany() {
        return billingCompany;
    }

    public void setBillingCompany(String billingCompany) {
        this.billingCompany = billingCompany;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingAddress1() {
        return billingAddress1;
    }

    public void setBillingAddress1(String billingAddress1) {
        this.billingAddress1 = billingAddress1;
    }

    public String getBillingAddress2() {
        return billingAddress2;
    }

    public void setBillingAddress2(String billingAddress2) {
        this.billingAddress2 = billingAddress2;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingPostcode() {
        return billingPostcode;
    }

    public void setBillingPostcode(String billingPostcode) {
        this.billingPostcode = billingPostcode;
    }

    public String getBillingPhone() {
        return billingPhone;
    }

    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public String getBillingEmail() {
        return billingEmail;
    }

    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

}
