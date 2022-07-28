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
class SignUpUnitTest {


    @Mock
    lateinit var mainViewModel:MainViewModel
    @Mock
   lateinit var repository : BaseAuthRepository

   @Before
   fun setUp(){
       mainViewModel = MainViewModel(repository)

   }

   /* @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2)
    }*/

    @Test
    fun validation_isCorrect() {
       val result = mainViewModel.signUpValidation("test@gmail.com","123456","123456")
       // assertThat(result,true)
        Assert.assertEquals(true, result)

    }

    @Test
    fun validation_isInCorrect() {
        val result = mainViewModel.signUpValidation("test@gmai.com","1234","123456")
        // assertThat(result,true)
        Assert.assertEquals(false, result)

    }

    @Test
    fun validation_isBlank() {
        val result = mainViewModel.signUpValidation("","","")
        // assertThat(result,true)
        Assert.assertEquals(false, result)

    }

}