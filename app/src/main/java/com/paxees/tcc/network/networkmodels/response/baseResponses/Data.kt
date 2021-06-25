package com.paxees.tcc.network.networkmodels.response.baseResponses

data class Data(
    val ID: String,
    val display_name: String,
    val user_activation_key: String,
    val user_email: String,
    val user_login: String,
    val user_nicename: String,
    val user_pass: String,
    val user_registered: String,
    val user_status: String,
    val user_url: String
)