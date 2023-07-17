package com.app.inventory.Models.ForgotPasseword

data class Message(
    val OTP: String,
    val message: String,
    val otpToken: String
)