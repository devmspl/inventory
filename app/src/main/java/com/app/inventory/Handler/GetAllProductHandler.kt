package com.app.inventory.Handler

import com.app.inventory.Models.GetAllProductModel.GetAllProductModel

interface GetAllProductHandler {
    fun onSuccess(getAllProductModel: GetAllProductModel?)
    fun onError(message: String?)

}