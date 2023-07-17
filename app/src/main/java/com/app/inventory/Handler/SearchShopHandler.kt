package com.app.inventory.Handler

import com.app.inventory.Models.SearchShopModel.SearchShopExample
import com.app.inventory.Models.SignUp.SignupExample

interface SearchShopHandler {
    fun onSuccess(searchShopExample: SearchShopExample)
    fun onError(message: String?)
}