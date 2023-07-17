package com.app.inventory.Handler

import com.app.inventory.Models.DeleteProductModel.DeleteProductExample

interface DeleteProductHandler {
    fun onSuccess(deletePeoductExample: DeleteProductExample)
    fun onError(message: String?)
}