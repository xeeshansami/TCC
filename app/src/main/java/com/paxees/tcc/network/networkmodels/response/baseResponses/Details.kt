package com.paxees.tcc.network.networkmodels.response.baseResponses


import com.google.gson.annotations.SerializedName

class Details {
    @SerializedName("Created_By")
    var createdBy: CreatedBy = CreatedBy()
    @SerializedName("Created_Time")
    var createdTime: String = ""
    @SerializedName("id")
    var id: String = ""
    @SerializedName("Modified_By")
    var modifiedBy: ModifiedBy = ModifiedBy()
    @SerializedName("Modified_Time")
    var modifiedTime: String = ""
}