package com.capg.insurance.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.capg.insurance.data.model.ProductModel
import com.capg.insurance.data.model.QuotesModel
import com.capg.insurance.data.repository.InsuranceRepository
import com.capg.insurance.interfaces.NetworkResponseCallback
import com.capg.insurance.utils.NetworkHelper

open class InsuranceViewModel(private val app: Application) : AndroidViewModel(app) {
    private var mProductList: MutableLiveData<List<ProductModel>> =
        MutableLiveData<List<ProductModel>>().apply { value = emptyList() }

    private var mQuotesList: MutableLiveData<List<QuotesModel>> =
        MutableLiveData<List<QuotesModel>>().apply { value = emptyList() }

    val mShowProgressBar = MutableLiveData(true)
    val mShowNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    val mShowApiError = MutableLiveData<String>()
    private var mRepository = InsuranceRepository.getInstance()

    // To fetch Product List
    fun getAllProducts(forceFetch: Boolean): MutableLiveData<List<ProductModel>> {
        if (NetworkHelper.isOnline(app.baseContext)) {
            mShowProgressBar.value = true
            mProductList = mRepository.getAllProducts(object : NetworkResponseCallback {
                override fun onNetworkFailure(th: Throwable) {
                    mShowApiError.value = th.message
                }

                override fun onNetworkSuccess() {
                    mShowProgressBar.value = false
                }
            }, forceFetch)
        } else {
            mShowNetworkError.value = true
        }
        return mProductList
    }

    // To fetch Quotes List
    fun getAllQuotes(forceFetch: Boolean): MutableLiveData<List<QuotesModel>> {
        if (NetworkHelper.isOnline(app.baseContext)) {
            mShowProgressBar.value = true
            mQuotesList = mRepository.getAllQuotes(object : NetworkResponseCallback {
                override fun onNetworkFailure(th: Throwable) {
                    mShowApiError.value = th.message
                }

                override fun onNetworkSuccess() {
                    mShowProgressBar.value = false
                }
            }, forceFetch)
        } else {
            mShowNetworkError.value = true
        }
        return mQuotesList
    }

}

