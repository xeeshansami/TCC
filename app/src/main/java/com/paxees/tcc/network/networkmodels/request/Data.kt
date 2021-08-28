package com.paxees.tcc.network.networkmodels.request


import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("Annual_Revenue")
    var annualRevenue: Double = 0.0
    @SerializedName("City")
    var city: String = ""
    @SerializedName("Country")
    var country: String = ""
    @SerializedName("Description")
    var description: String = ""
    @SerializedName("Designation")
    var designation: String = ""
    @SerializedName("Email")
    var email: String = ""
    @SerializedName("Email_Opt_Out")
    var emailOptOut: Boolean = false
    @SerializedName("Fax")
    var fax: String = ""
    @SerializedName("First_Name")
    var firstName: String = ""
    @SerializedName("How_did_you_find_us")
    var howDidYouFindUs: String = ""
    @SerializedName("I_have_read_understood_the_following")
    var iHaveReadUnderstoodTheFollowing: String = ""
    @SerializedName("I_m_interested_in_cannabis_plants")
    var iMInterestedInCannabisPlants: String = ""
    @SerializedName("I_m_interested_in_cannabis_seeds")
    var iMInterestedInCannabisSeeds: String = ""
    @SerializedName("I_m_interested_in_other_products")
    var iMInterestedInOtherProducts: String = ""
    @SerializedName("Industry")
    var industry: String = ""
    @SerializedName("Last_Name")
    var lastName: String = ""
    @SerializedName("Lead_Source")
    var leadSource: String = ""
    @SerializedName("Lead_Status")
    var leadStatus: String = ""
    @SerializedName("Medical_Rec")
    var medicalRec: String = ""
    @SerializedName("Mobile")
    var mobile: String = ""
    @SerializedName("No_of_Employees")
    var noOfEmployees: Int = 0
    @SerializedName("Owner")
    var owner: Owner = Owner()
    @SerializedName("Phone")
    var phone: String = ""
    @SerializedName("Rating")
    var rating: String = ""
    @SerializedName("Recommendation_Website")
    var recommendationWebsite: String = ""
    @SerializedName("Salutation")
    var salutation: String = ""
    @SerializedName("Secondary_Email")
    var secondaryEmail: String = ""
    @SerializedName("Skype_ID")
    var skypeID: String = ""
    @SerializedName("State")
    var state: String = ""
    @SerializedName("Street")
    var street: String = ""
    @SerializedName("Twitter")
    var twitter: String = ""
    @SerializedName("Type_of_user")
    var typeOfUser: String = ""
    @SerializedName("Website")
    var website: String = ""
    @SerializedName("Zip_Code")
    var zipCode: String = ""
}