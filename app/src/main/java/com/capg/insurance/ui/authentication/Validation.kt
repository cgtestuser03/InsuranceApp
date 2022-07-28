package com.capg.insurance.ui.authentication

import android.text.TextUtils
import android.util.Patterns
import com.capg.insurance.viewmodels.MainViewModel
import java.util.regex.Pattern

object Validation {

    fun loginValidation(email: String , password: String):Boolean{

        when {
            email.isEmpty() -> {
                return false
            }
            password.isEmpty() -> {
                return false
            }

            password.length < 5 -> {
                return false
            }

            !EMAIL_ADDRESS_PATTERN.matcher(email).matches() -> {
                return false
            }

            else -> {
                return true
            }
        }


    }


    fun registrationValidation(email: String , password: String, confirmPass : String):Boolean{

        when{
            email.isEmpty() -> {
                return false
            }
            password.isEmpty() -> {
                return false
            }
            password != confirmPass ->{
                return false
            }
            password.length < 5 -> {
                return false
            }

            !EMAIL_ADDRESS_PATTERN.matcher(email).matches() -> {
                return false
            }

            else -> {
                return true
            }
        }

    }

    fun formValidation_Name(name:String):Boolean{

        if(name.length >3){
            return true
        }else{
            return false
        }
    }

    fun formValidation_Dob(dob:String):Boolean{

        if(dob.isEmpty()){
            return false
        }else{
            return true
        }
    }

    fun formValidation_Phone(phone:String):Boolean{

        if(phone.length < 10 || phone.length >10){
            return false
        }else{
            return true
        }
    }

    fun formValidation_Gender(gender:Boolean):Boolean{

        if(gender == false){
            return false
        }else{
            return true
        }
    }


    fun formValidationTesting(name: String , dob: String, phone : String, gender:Boolean):Boolean{
        return if(name.length < 3){
            false
        }else if(dob.isEmpty()){
            false

        }else if(phone.length < 10 || phone.length >10){
            false
        }else gender

    }

        val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

}