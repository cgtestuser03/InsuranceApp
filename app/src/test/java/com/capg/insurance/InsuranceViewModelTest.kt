package com.cg.getgrocery

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.capg.insurance.data.model.ProductModel
import com.capg.insurance.data.repository.InsuranceRepository
import com.capg.insurance.interfaces.NetworkResponseCallback
import com.capg.insurance.viewmodels.InsuranceViewModel

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)

class InsuranceViewModelTest {
    @Mock
     lateinit var insuranceViewModel: InsuranceViewModel

     @Mock
     lateinit var application: Application

     @Mock
     lateinit var insuranceRepository: InsuranceRepository

     lateinit var dummyData: MutableLiveData<List<ProductModel>>
     lateinit var originalData: MutableLiveData<List<ProductModel>>

    @Before
    fun setup() {
        insuranceViewModel = InsuranceViewModel(application)
        insuranceRepository = InsuranceRepository.getInstance()
        //dummyData = MutableLiveData<List<ProductModel>>().apply { value = emptyList() }
        dummyData = MutableLiveData<List<ProductModel>>()
        originalData = MutableLiveData<List<ProductModel>>()

    }
    @Spy // mock it partially
    @InjectMocks
    val mock: InsuranceRepository = Mockito.mock(InsuranceRepository::class.java)


    @Test
    fun getAllProductsc() {
        dummyData =  insuranceRepository.getAllProducts(object : NetworkResponseCallback {
            override fun onNetworkFailure(th: Throwable) {
            }

            override fun onNetworkSuccess() {
            }
        }, false)

        originalData = mock.getAllProducts(object : NetworkResponseCallback {
            override fun onNetworkFailure(th: Throwable) {
            }

            override fun onNetworkSuccess() {
            }
        }, false)

        Mockito.`when`(originalData)
            .thenReturn(dummyData/*ResultWrapper.Success(CategoryResponse(ArrayList()))*/)
        // mainViewModel.getCategories()
        // val result = mainViewModel.categoriesResult /*as CategoryResponse*/
        assertEquals(dummyData, originalData)

    }





}