package com.app.inventory.Models.OtpVerification

data class OtpExample(
    val isSuccess: Boolean,
    val message: Message,
    val statusCode: Int
)