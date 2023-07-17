package com.app.inventory.Handler

import com.app.inventory.Models.UpdateShopImageExample.UpdateShopImageExample

interface UpdateShopImageImageHandler {
    fun onSuccess(updateShopImageExample: UpdateShopImageExample?)
    fun onError(message: String?)
}