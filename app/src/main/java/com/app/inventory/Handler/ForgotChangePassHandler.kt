package com.app.inventory.Handler

import com.app.inventory.Models.ForgotChangePassword.ForgotChangePassExample
import com.app.inventory.Models.LoginExample

interface ForgotChangePassHandler {
    fun onSuccess(forgotChangePassExample: ForgotChangePassExample?)
    fun onError(message: String?)
}