package com.app.inventory.Handler

import com.app.inventory.Models.AddProductModel.AddProductExample

interface AddProductHandler {
    fun onSuccess(addProductExample: AddProductExample?)
    fun onError(message: String?)
}