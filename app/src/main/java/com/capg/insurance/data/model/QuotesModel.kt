package com.capg.insurance.data.model

import com.google.gson.annotations.SerializedName

class QuotesModel (@SerializedName("url") val url:String,
                   @SerializedName("id") val id: String,
                   @SerializedName("name") val name: String,
                   @SerializedName("price") val price: String,
                   @SerializedName("claim") val claim: String,
                   @SerializedName("offer") val offer: String)