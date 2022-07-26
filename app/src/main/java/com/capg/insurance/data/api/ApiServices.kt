package com.capg.insurance.data.api

import com.capg.insurance.data.model.ProductModel
import com.capg.insurance.data.model.QuotesModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("insurancelist.json")
    fun getAllProducts(): Call<List<ProductModel>>

    @GET("qutoslist.json")
    fun getAllQuotes(): Call<List<QuotesModel>>
}