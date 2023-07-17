package com.app.inventory.Handler

import com.app.inventory.Models.ProductUpdateByiD.ProductUpdateByIdExample
import com.app.inventory.Models.ProductUpdateImageModel.ProductImageUpdateExample

interface UpdateProductImageHandler {
    fun onSuccess(productImageUpdateExample: ProductImageUpdateExample ?)
    fun onError(message: String?)
}