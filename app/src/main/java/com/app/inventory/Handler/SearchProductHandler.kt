package com.app.inventory.Handler

import com.app.inventory.Models.SearchproductModel.SearchproductExample
import com.app.inventory.Models.SignUp.SignupExample

interface SearchProductHandler {
    fun onSuccess(searchproductExample: SearchproductExample)
    fun onError(message: String?)
}