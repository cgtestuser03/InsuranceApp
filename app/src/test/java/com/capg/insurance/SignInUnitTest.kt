package com.capg.insurance

import android.util.Log
import com.capg.insurance.data.repository.BaseAuthRepository
import com.capg.insurance.ui.authentication.SignInFragment
import com.capg.insurance.viewmodels.MainViewModel
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SignInUnitTest {


    @Mock
    lateinit var mainViewModel:MainViewModel
    @Mock
   lateinit var repository : BaseAuthRepository

   @Before
   fun setUp(){
       mainViewModel = MainViewModel(repository)

   }

    @Test
    fun validation_isCorrect() {
       val result = mainViewModel.signInValidation("test@gmail.com","123456")
       // assertThat(result,true)
        Assert.assertEquals(true, result)

    }

    @Test
    fun validation_isInCorrect() {
        val result = mainViewModel.signInValidation("abc@gmail.com","1234")
        // assertThat(result,true)
        Assert.assertEquals(false, result)

    }

}