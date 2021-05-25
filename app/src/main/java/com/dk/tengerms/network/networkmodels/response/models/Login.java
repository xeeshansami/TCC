
package com.dk.tengerms.network.networkmodels.response.models;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login implements Parcelable {

    @SerializedName("cn")
    @Expose
    private String cn;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("distinguishedName")
    @Expose
    private String distinguishedName;
    @SerializedName("dn")
    @Expose
    private String dn;
    @SerializedName("givenName")
    @Expose
    private String givenName;
    @SerializedName("initials")
    @Expose
    private String initials;
    @SerializedName("lockoutTime")
    @Expose
    private String lockoutTime;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("pwdLastSet")
    @Expose
    private String pwdLastSet;
    @SerializedName("sAMAccountName")
    @Expose
    private String sAMAccountName;
    @SerializedName("sn")
    @Expose
    private String sn;
    @SerializedName("userAccountControl")
    @Expose
    private String userAccountControl;
    @SerializedName("userPrincipalName")
    @Expose
    private String userPrincipalName;
    @SerializedName("whenCreated")
    @Expose
    private String whenCreated;
    @SerializedName("manager")
    @Expose
    private String manager;
    @SerializedName("streetAddress")
    @Expose
    private String streetAddress;
    @SerializedName("physicalDeliveryOfficeName")
    @Expose
    private String physicalDeliveryOfficeName;
    @SerializedName("telephoneNumber")
    @Expose
    private String telephoneNumber;
    @SerializedName("l")
    @Expose
    private String l;
    @SerializedName("st")
    @Expose
    private String st;
    @SerializedName("co")
    @Expose
    private String co;
    @SerializedName("c")
    @Expose
    private String c;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("memberOf")
    @Expose
    private List<String> memberOf = null;
    @SerializedName("accountExpires")
    @Expose
    private String accountExpires;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("submissionContLength")
    @Expose
    private String submissionContLength;
    @SerializedName("delivContLength")
    @Expose
    private String delivContLength;
    @SerializedName("msExchRecipLimit")
    @Expose
    private String msExchRecipLimit;
    @SerializedName("msExchOmaAdminWirelessEnable")
    @Expose
    private String msExchOmaAdminWirelessEnable;
    @SerializedName("protocolSettings")
    @Expose
    private List<String> protocolSettings = null;
    @SerializedName("primaryGroupID")
    @Expose
    private String primaryGroupID;
    @SerializedName("msExchHomeServerName")
    @Expose
    private String msExchHomeServerName;
    @SerializedName("proxyAddresses")
    @Expose
    private List<String> proxyAddresses = null;
    public final static Creator<Login> CREATOR = new Creator<Login>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Login createFromParcel(Parcel in) {
            return new Login(in);
        }

        public Login[] newArray(int size) {
            return (new Login[size]);
        }

    };

    protected Login(Parcel in) {
        this.cn = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.displayName = ((String) in.readValue((String.class.getClassLoader())));
        this.distinguishedName = ((String) in.readValue((String.class.getClassLoader())));
        this.dn = ((String) in.readValue((String.class.getClassLoader())));
        this.givenName = ((String) in.readValue((String.class.getClassLoader())));
        this.initials = ((String) in.readValue((String.class.getClassLoader())));
        this.lockoutTime = ((String) in.readValue((String.class.getClassLoader())));
        this.mail = ((String) in.readValue((String.class.getClassLoader())));
        this.pwdLastSet = ((String) in.readValue((String.class.getClassLoader())));
        this.sAMAccountName = ((String) in.readValue((String.class.getClassLoader())));
        this.sn = ((String) in.readValue((String.class.getClassLoader())));
        this.userAccountControl = ((String) in.readValue((String.class.getClassLoader())));
        this.userPrincipalName = ((String) in.readValue((String.class.getClassLoader())));
        this.whenCreated = ((String) in.readValue((String.class.getClassLoader())));
        this.manager = ((String) in.readValue((String.class.getClassLoader())));
        this.streetAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.physicalDeliveryOfficeName = ((String) in.readValue((String.class.getClassLoader())));
        this.telephoneNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.l = ((String) in.readValue((String.class.getClassLoader())));
        this.st = ((String) in.readValue((String.class.getClassLoader())));
        this.co = ((String) in.readValue((String.class.getClassLoader())));
        this.c = ((String) in.readValue((String.class.getClassLoader())));
        this.countryCode = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.memberOf, (String.class.getClassLoader()));
        this.accountExpires = ((String) in.readValue((String.class.getClassLoader())));
        this.info = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.department = ((String) in.readValue((String.class.getClassLoader())));
        this.company = ((String) in.readValue((String.class.getClassLoader())));
        this.submissionContLength = ((String) in.readValue((String.class.getClassLoader())));
        this.delivContLength = ((String) in.readValue((String.class.getClassLoader())));
        this.msExchRecipLimit = ((String) in.readValue((String.class.getClassLoader())));
        this.msExchOmaAdminWirelessEnable = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.protocolSettings, (String.class.getClassLoader()));
        this.primaryGroupID = ((String) in.readValue((String.class.getClassLoader())));
        this.msExchHomeServerName = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.proxyAddresses, (String.class.getClassLoader()));
    }

    public Login() {
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDistinguishedName() {
        return distinguishedName;
    }

    public void setDistinguishedName(String distinguishedName) {
        this.distinguishedName = distinguishedName;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getLockoutTime() {
        return lockoutTime;
    }

    public void setLockoutTime(String lockoutTime) {
        this.lockoutTime = lockoutTime;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwdLastSet() {
        return pwdLastSet;
    }

    public void setPwdLastSet(String pwdLastSet) {
        this.pwdLastSet = pwdLastSet;
    }

    public String getSAMAccountName() {
        return sAMAccountName;
    }

    public void setSAMAccountName(String sAMAccountName) {
        this.sAMAccountName = sAMAccountName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getUserAccountControl() {
        return userAccountControl;
    }

    public void setUserAccountControl(String userAccountControl) {
        this.userAccountControl = userAccountControl;
    }

    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public void setUserPrincipalName(String userPrincipalName) {
        this.userPrincipalName = userPrincipalName;
    }

    public String getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(String whenCreated) {
        this.whenCreated = whenCreated;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPhysicalDeliveryOfficeName() {
        return physicalDeliveryOfficeName;
    }

    public void setPhysicalDeliveryOfficeName(String physicalDeliveryOfficeName) {
        this.physicalDeliveryOfficeName = physicalDeliveryOfficeName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<String> getMemberOf() {
        return memberOf;
    }

    public void setMemberOf(List<String> memberOf) {
        this.memberOf = memberOf;
    }

    public String getAccountExpires() {
        return accountExpires;
    }

    public void setAccountExpires(String accountExpires) {
        this.accountExpires = accountExpires;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSubmissionContLength() {
        return submissionContLength;
    }

    public void setSubmissionContLength(String submissionContLength) {
        this.submissionContLength = submissionContLength;
    }

    public String getDelivContLength() {
        return delivContLength;
    }

    public void setDelivContLength(String delivContLength) {
        this.delivContLength = delivContLength;
    }

    public String getMsExchRecipLimit() {
        return msExchRecipLimit;
    }

    public void setMsExchRecipLimit(String msExchRecipLimit) {
        this.msExchRecipLimit = msExchRecipLimit;
    }

    public String getMsExchOmaAdminWirelessEnable() {
        return msExchOmaAdminWirelessEnable;
    }

    public void setMsExchOmaAdminWirelessEnable(String msExchOmaAdminWirelessEnable) {
        this.msExchOmaAdminWirelessEnable = msExchOmaAdminWirelessEnable;
    }

    public List<String> getProtocolSettings() {
        return protocolSettings;
    }

    public void setProtocolSettings(List<String> protocolSettings) {
        this.protocolSettings = protocolSettings;
    }

    public String getPrimaryGroupID() {
        return primaryGroupID;
    }

    public void setPrimaryGroupID(String primaryGroupID) {
        this.primaryGroupID = primaryGroupID;
    }

    public String getMsExchHomeServerName() {
        return msExchHomeServerName;
    }

    public void setMsExchHomeServerName(String msExchHomeServerName) {
        this.msExchHomeServerName = msExchHomeServerName;
    }

    public List<String> getProxyAddresses() {
        return proxyAddresses;
    }

    public void setProxyAddresses(List<String> proxyAddresses) {
        this.proxyAddresses = proxyAddresses;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cn);
        dest.writeValue(description);
        dest.writeValue(displayName);
        dest.writeValue(distinguishedName);
        dest.writeValue(dn);
        dest.writeValue(givenName);
        dest.writeValue(initials);
        dest.writeValue(lockoutTime);
        dest.writeValue(mail);
        dest.writeValue(pwdLastSet);
        dest.writeValue(sAMAccountName);
        dest.writeValue(sn);
        dest.writeValue(userAccountControl);
        dest.writeValue(userPrincipalName);
        dest.writeValue(whenCreated);
        dest.writeValue(manager);
        dest.writeValue(streetAddress);
        dest.writeValue(physicalDeliveryOfficeName);
        dest.writeValue(telephoneNumber);
        dest.writeValue(l);
        dest.writeValue(st);
        dest.writeValue(co);
        dest.writeValue(c);
        dest.writeValue(countryCode);
        dest.writeList(memberOf);
        dest.writeValue(accountExpires);
        dest.writeValue(info);
        dest.writeValue(title);
        dest.writeValue(department);
        dest.writeValue(company);
        dest.writeValue(submissionContLength);
        dest.writeValue(delivContLength);
        dest.writeValue(msExchRecipLimit);
        dest.writeValue(msExchOmaAdminWirelessEnable);
        dest.writeList(protocolSettings);
        dest.writeValue(primaryGroupID);
        dest.writeValue(msExchHomeServerName);
        dest.writeList(proxyAddresses);
    }

    public int describeContents() {
        return 0;
    }

}
