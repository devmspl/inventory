package com.app.inventory.Handler

import com.app.inventory.Models.AddShopModel.AddShopModel

interface AddShopHandler {
    fun onSuccess(addShopExample: AddShopModel?, owner:String?)
    fun onError(message: String?)
}