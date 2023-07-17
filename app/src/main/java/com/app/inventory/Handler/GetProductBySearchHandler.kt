package com.app.inventory.Handler

import com.app.inventory.Models.ProductSearchByShopIdModel.ProductSearchByShopId

interface GetProductBySearchHandler {
    fun onSuccess(getProductBySearchExample: ProductSearchByShopId?)
    fun onError(message: String?)
}