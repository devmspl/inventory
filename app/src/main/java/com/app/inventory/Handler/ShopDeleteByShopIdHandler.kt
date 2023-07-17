package com.app.inventory.Handler

import com.app.inventory.Models.RoleModel.RoleExample
import com.app.inventory.Models.ShopDeleteByShopIdModel.ShopDeleteByShopIdExample

interface ShopDeleteByShopIdHandler {
    fun onSuccess(shopDeleteByShopIdExample: ShopDeleteByShopIdExample?)
    fun onError(message: String?)
}
