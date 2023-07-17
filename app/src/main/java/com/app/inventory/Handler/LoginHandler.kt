package com.app.inventory.Handler

import com.app.inventory.Models.LoginExample

interface LoginHandler {
    fun onSuccess(loginExample: LoginExample?, acesstoken: String?)
    fun onError(message: String?)
}