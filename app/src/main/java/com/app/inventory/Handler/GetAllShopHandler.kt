package com.app.inventory.Handler

import com.app.inventory.Models.GetAllShopModel.GetAllShopModel

interface GetAllShopHandler {
    fun onSuccess(getAllShopModel: GetAllShopModel?)
    fun onError(message: String?)
}