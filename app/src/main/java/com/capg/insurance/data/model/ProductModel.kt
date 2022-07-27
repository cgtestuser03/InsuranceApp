package com.capg.insurance.data.model

import com.google.gson.annotations.SerializedName

data class ProductModel (@SerializedName("url") val url:String,
                         @SerializedName("id") val id: String,
                         @SerializedName("name") val name: String,
                         @SerializedName("desc") val desc: String)
