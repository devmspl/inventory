package com.app.inventory.Handler

import com.app.inventory.Models.UpdateShopModel.UpdateShopExample

interface UpdateShopHandler {
    fun onSuccess(updateShopExample: UpdateShopExample)
    fun onError(message: String?)
}