package com.capg.insurance.data.repository

import androidx.lifecycle.MutableLiveData
import com.capg.insurance.data.api.RestClient
import com.capg.insurance.data.model.ProductModel
import com.capg.insurance.data.model.QuotesModel
import com.capg.insurance.interfaces.NetworkResponseCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsuranceRepository private constructor() {

    private lateinit var mCallback: NetworkResponseCallback
    private var productList: MutableLiveData<List<ProductModel>> =
        MutableLiveData<List<ProductModel>>().apply { value = emptyList() }

    private var quotesList: MutableLiveData<List<QuotesModel>> =
        MutableLiveData<List<QuotesModel>>().apply { value = emptyList() }

    companion object {
        private var mInstance: InsuranceRepository? = null
        fun getInstance(): InsuranceRepository {
            if (mInstance == null) {
                synchronized(this) {
                    mInstance = InsuranceRepository()
                }
            }
            return mInstance!!
        }
    }

    // To fetch Product list
    private lateinit var mProductCall: Call<List<ProductModel>>

    fun getAllProducts(callback: NetworkResponseCallback, forceFetch : Boolean): MutableLiveData<List<ProductModel>> {
        mCallback = callback
        if (productList.value!!.isNotEmpty() && !forceFetch) {
            mCallback.onNetworkSuccess()
            return productList
        }
        mProductCall = RestClient.getInstance().getApiService().getAllProducts()
        mProductCall.enqueue(object : Callback<List<ProductModel>> {

            override fun onResponse(call: Call<List<ProductModel>>, response: Response<List<ProductModel>>) {
                productList.value = response.body()
                mCallback.onNetworkSuccess()
            }

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                productList.value = emptyList()
                mCallback.onNetworkFailure(t)
            }

        })
        return productList
    }


    // To fetch Quotes list
    private lateinit var mQuotesCall: Call<List<QuotesModel>>

    fun getAllQuotes(callback: NetworkResponseCallback, forceFetch : Boolean): MutableLiveData<List<QuotesModel>> {
        mCallback = callback
        if (quotesList.value!!.isNotEmpty() && !forceFetch) {
            mCallback.onNetworkSuccess()
            return quotesList
        }
        mQuotesCall = RestClient.getInstance().getApiService().getAllQuotes()
        mQuotesCall.enqueue(object : Callback<List<QuotesModel>> {

            override fun onResponse(call: Call<List<QuotesModel>>, response: Response<List<QuotesModel>>) {
                quotesList.value = response.body()
                mCallback.onNetworkSuccess()
            }

            override fun onFailure(call: Call<List<QuotesModel>>, t: Throwable) {
                quotesList.value = emptyList()
                mCallback.onNetworkFailure(t)
            }

        })
        return quotesList
    }
}