package com.example.loginpage

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AccountViewModel: ViewModel() {

    var createUser: NewAccount = NewAccount()

    var userName:MutableState<String> = mutableStateOf(createUser.name)
    var isUserNameValid:MutableState<Boolean> = mutableStateOf(false)
    var userNameErrMsg:MutableState<String> = mutableStateOf("")

    var email:MutableState<String> = mutableStateOf(createUser.email)
    var isEmailValid:MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg:MutableState<String> = mutableStateOf("")

    var phoneNo:MutableState<String> = mutableStateOf(createUser.phoneNo)
    var isPhoneNoValid:MutableState<Boolean> = mutableStateOf(false)
    var phoneNoErrMsg:MutableState<String> = mutableStateOf("")

    var password:MutableState<String> = mutableStateOf(createUser.password)
    var isPasswordValid:MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg:MutableState<String> = mutableStateOf("")

    var confirmPassword:MutableState<String> = mutableStateOf(createUser.confirmPassword)
    var isConfirmPasswordValid:MutableState<Boolean> = mutableStateOf(false)
    var confirmPasswordErrMsg:MutableState<String> = mutableStateOf("")

    var isEnabledCreateButton:MutableState<Boolean> = mutableStateOf(false)

    init{ }

    private fun shouldEnabledCreateButton(){
        isEnabledCreateButton.value = userNameErrMsg.value.isEmpty()
                && emailErrMsg.value.isEmpty()
                //&& phoneNoErrMsg.value.isEmpty()
                && passwordErrMsg.value.isEmpty()
                && confirmPassword.value.isEmpty()
                //&& userNameErrMsg.value.isNotEmpty()
                //&& emailErrMsg.value.isNotEmpty()
                //&& phoneNoErrMsg.value.isNotEmpty()
                //&& passwordErrMsg.value.isNotEmpty()
                //&& confirmPassword.value.isNotEmpty()
    }
    fun validateUserName(){
        if (userName.value.length >= 10){
            isUserNameValid.value = true
            userNameErrMsg.value ="User Name Should be less then 10 chars"
        } else{
            isUserNameValid.value = false
            userNameErrMsg.value =  ""
        }
        shouldEnabledCreateButton()
    }
    fun validateEmail(){
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            isEmailValid.value = true
            emailErrMsg.value = "Input proper email id"
        } else {
            isEmailValid.value = false
            emailErrMsg.value = ""
        }
        shouldEnabledCreateButton()
    }
    fun validatePhoneNo(){
        if (android.util.Patterns.PHONE.matcher(phoneNo.value).matches()){
            isPhoneNoValid.value = true
            phoneNoErrMsg.value = "Phone Number is valid"
        } else{
            isPhoneNoValid.value = false
            phoneNoErrMsg.value = "Phone Number is invalid"
        }
        shouldEnabledCreateButton()
    }
    fun validatePassword(){
        if (password.value !="123"){
            isPasswordValid.value = true
            passwordErrMsg.value = "Password should be 123"
        } else {
            isPasswordValid.value = false
            passwordErrMsg.value = ""
        }
        shouldEnabledCreateButton()
    }
    fun validateConfirmPassword(){
        if (password.value != confirmPassword.value){
            isConfirmPasswordValid.value = true
            confirmPasswordErrMsg.value = "Password did not match"
        } else{
            isConfirmPasswordValid.value = false
            confirmPassword.value = ""
        }
        shouldEnabledCreateButton()
    }
    fun Create(){
        createUser.name = userName.value
        createUser.email = email.value
        //createUser.phoneNo = phoneNo.value
        createUser.password = password.value
        createUser.confirmPassword = confirmPassword.value
        Log.d("Akash",userName.value)
        Log.d("Akash",email.value)
        //Log.d("Akash",phoneNo.value)
        Log.d("Akash",password.value)
        Log.d("Akash",confirmPassword.value)
        Log.d("Akash",createUser.toString())

    }

}