package com.capg.insurance

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class SignUpPageTest {

    @get:Rule
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup()
    {
        mActivityTestRule.scenario.moveToState(Lifecycle.State.RESUMED)

        val signUp = onView(
            allOf(
                withId(R.id.sign_up_txt), withText("Sign up here"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        signUp.check(matches(isDisplayed()))
        signUp.perform(click())
    }

    @Test
    fun checkSignUpTitlePresent() {

        val textview = onView(
            allOf(withId(R.id.textView), withText("Sign up"),
                withParent(withId(R.id.layoutSignUpParent)),
                isDisplayed()))
    }

    @Test
    fun userEmailFieldExist() {

        val email = onView(
            allOf(withId(R.id.user_email_etv),
                withParent(withId(R.id.layoutSignUpParent)),
                isDisplayed()))
    }

    @Test
    fun userPasswordFieldExist() {

        val password = onView(
            allOf(withId(R.id.user_password_etv),
                withParent(withId(R.id.layoutSignUpParent)),
                isDisplayed()))
    }

    @Test
    fun userConfirmFieldExist() {

        val confirm = onView(
            allOf(withId(R.id.confirm_password_etv),
                withParent(withId(R.id.layoutSignUpParent)),
                isDisplayed()))
    }

    @Test
    fun signUpButtonExist() {

        val signBtn = onView(
            allOf(withId(R.id.sign_up_button),
                withParent(withId(R.id.layoutSignUpParent)),
                isDisplayed()))
    }

    @Test
    fun havingAccountTextIsExist() {

        val textView = onView(
            allOf(withId(R.id.textView4),
                withParent(withId(R.id.layoutSignUpParent)),
                isDisplayed()))

        textView.check(matches(withText("Already have an account?")))
    }

    @Test
    fun signInTextIsExist() {

        val textView = onView(
            allOf(withId(R.id.sign_in_txt),
                withParent(withId(R.id.layoutSignUpParent)),
                isDisplayed()))

        textView.check(matches(withText("Sign in here")))
    }

    @Test
    fun checkEmailFieldAcceptingInput()
    {
        onView(withId(R.id.user_email_etv)).perform(typeText("insurance.test@gmail.com"), closeSoftKeyboard())
    }

    @Test
    fun checkPasswordFieldAcceptingInput() {
        onView(withId(R.id.user_password_etv)).perform(typeText("Test@123"), closeSoftKeyboard())
    }

    @Test
    fun checkConfirmPasswordFieldAcceptingInput() {
        onView(withId(R.id.confirm_password_etv)).perform(typeText("Test@123"), closeSoftKeyboard())
    }
}
