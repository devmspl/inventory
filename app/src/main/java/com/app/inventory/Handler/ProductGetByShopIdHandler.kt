package com.app.inventory.Handler

import com.app.inventory.Models.ProductGetByShopid.ProductGetByShopIdExample

interface ProductGetByShopIdHandler {
    fun onSuccess(productGetByShopIdExample: ProductGetByShopIdExample?)
    fun onError(message: String?)
}