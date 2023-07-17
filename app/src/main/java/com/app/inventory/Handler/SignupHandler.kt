package com.app.inventory.Handler

import com.app.inventory.Models.SignUp.SignupExample

interface SignupHandler {
    fun onSuccess(signupExample: SignupExample?, deviceToken: String?)
    fun onError(message: String?)
}