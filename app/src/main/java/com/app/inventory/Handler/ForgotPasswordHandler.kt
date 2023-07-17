package com.app.inventory.Handler

import com.app.inventory.Models.ForgotPasseword.ForgotPassExample

interface ForgotPasswordHandler {
    fun onSuccess(forgotPasswordHandler: ForgotPassExample?)
    fun onError(message: String?)
}