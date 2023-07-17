package com.app.inventory.Handler

import com.app.inventory.Models.UploadProductImage.UploadProductImageModel

interface UploadProductImageHandler {
    fun onSuccess(uploadProductImageModel: UploadProductImageModel?)
    fun onError(message: String?)
}