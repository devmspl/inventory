package com.app.inventory.Handler

import com.app.inventory.Models.ForgotPasseword.ForgotPassExample
import com.app.inventory.Models.OtpVerification.OtpExample

interface OtpVerificationHandler {
    fun onSuccess(otpExample: OtpExample?)
    fun onError(message: String?)
}