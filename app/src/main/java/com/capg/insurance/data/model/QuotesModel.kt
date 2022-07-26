package com.capg.insurance.data.model

import com.google.gson.annotations.SerializedName

class QuotesModel (@SerializedName("image") val image:Int,
                   @SerializedName("id") val id: String,
                   @SerializedName("name") val name: String,
                   @SerializedName("price") val price: String,
                   @SerializedName("claim") val claim: String)