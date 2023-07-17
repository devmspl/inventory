package com.app.inventory.Handler

import com.app.inventory.Models.UploadshopImageModel.UploadShopImageExample
interface UploadProfileImageHandler {
    fun onSuccess(uploadShopImageExample: UploadShopImageExample?)
    fun onError(message: String?)
}