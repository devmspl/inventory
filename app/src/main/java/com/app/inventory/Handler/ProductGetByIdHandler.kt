package com.app.inventory.Handler

import com.app.inventory.Models.ProductGetById.ProductGetByIdExample

interface ProductGetByIdHandler {
    fun onSuccess(productGetByIdExample: ProductGetByIdExample?)
    fun onError(message: String?)
}