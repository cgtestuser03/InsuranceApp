package com.capg.insurance.data.model

import com.google.gson.annotations.SerializedName

data class ProductModel (@SerializedName("image") val image:Int,
                         @SerializedName("id") val id: String,
                         @SerializedName("name") val name: String,
                         @SerializedName("desc") val desc: String)
