package com.capg.insurance

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class SignInPageTest {

    @get:Rule
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        mActivityTestRule.scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun checkSignInTitlePresent() {

        val textview = onView(
            allOf(
                withId(R.id.textView), withText("Sign in"),
                withParent(withId(R.id.layoutSignInParent)),
                isDisplayed()
            )
        )
    }

    @Test
    fun userEmailFieldExist() {

        val signEdt = onView(
            allOf(
                withId(R.id.user_email_etv),
                withParent(withId(R.id.layoutSignInParent)),
                isDisplayed()
            )
        )
    }

    @Test
    fun userPasswordFieldExist() {

        val userPassword = onView(
            allOf(
                withId(R.id.user_password_etv),
                withParent(withId(R.id.layoutSignInParent)),
                isDisplayed()
            )
        )
    }

    @Test
    fun signButtonExist() {

        val signBtn = onView(
            allOf(
                withId(R.id.sign_in_button),
                withParent(withId(R.id.layoutSignInParent)),
                isDisplayed()
            )
        )
    }

    @Test
    fun checkEmailFieldAcceptingInput() {
        val emailId = onView(withId(R.id.user_email_etv))
        emailId.perform(typeText("insurance.test@gmail.com"), closeSoftKeyboard())
    }

    @Test
    fun checkPasswordFieldAcceptingInput() {
        val password = onView(withId(R.id.user_password_etv))
        password.perform(typeText("Test@123"), closeSoftKeyboard())
    }

    @Test
    fun signUpSuccessful()
    {

        val emailId = onView(withId(R.id.user_email_etv))
        emailId.perform(typeText("insurance.test@gmail.com"), closeSoftKeyboard())

        val password = onView(withId(R.id.user_password_etv))
        password.perform(typeText("Test@123"), closeSoftKeyboard())

        val signBtn = onView(
            allOf(withId(R.id.sign_in_button),
                withParent(withId(R.id.layoutSignInParent)),
                isDisplayed()))

          signBtn.perform(click())

    }
}
