package com.paxees.tcc.network.networkmodels.response.baseResponses

 class Links(
         val about: List<About>,
         val collection: List<Collection>,
         val curies: List<Cury>,
         val self: List<Self>,
         val wp: ArrayList<WpPostType> = ArrayList<WpPostType>()
)