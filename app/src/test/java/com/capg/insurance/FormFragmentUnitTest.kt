package com.capg.insurance

import android.util.Log
import com.capg.insurance.data.repository.BaseAuthRepository
import com.capg.insurance.ui.authentication.SignInFragment
import com.capg.insurance.ui.authentication.Validation
import com.capg.insurance.ui.view.FormFragment
import com.capg.insurance.viewmodels.MainViewModel
import kotlinx.coroutines.flow.combine
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FormFragmentUnitTest {


    @Mock
    lateinit var formFragment: FormFragment

   @Before
   fun setUp(){
       formFragment = FormFragment()
   }

    @Test
    fun validation_isCorrect() {
       val result = Validation.formValidationTesting("RANJAN","23/01/1991","8100399567",true)
       // assertThat(result,true)
        Assert.assertEquals(true, result)

    }

    @Test
    fun validation_NotCorrect() {
        val result = Validation.formValidationTesting("","23/01/1991","8100399567",true)
        // assertThat(result,true)
        Assert.assertEquals(false, result)

    }


}