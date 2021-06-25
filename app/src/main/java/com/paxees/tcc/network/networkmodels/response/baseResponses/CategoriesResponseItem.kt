package com.paxees.tcc.network.networkmodels.response.baseResponses

data class CategoriesResponseItem(
    val _links: Links,
    val count: Int,
    val description: String,
    val id: Int,
    val link: String,
    val meta: List<Any>,
    val name: String,
    val parent: Int,
    val slug: String,
    val taxonomy: String,
    val yoast_head: String
)