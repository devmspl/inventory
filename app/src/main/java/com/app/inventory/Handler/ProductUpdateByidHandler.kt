package com.app.inventory.Handler

import com.app.inventory.Models.ProductSearchByShopIdModel.ProductSearchByShopId
import com.app.inventory.Models.ProductUpdateByiD.ProductUpdateByIdExample

interface ProductUpdateByidHandler {
    fun onSuccess(productUpdateByIdExample: ProductUpdateByIdExample?)
    fun onError(message: String?)
}