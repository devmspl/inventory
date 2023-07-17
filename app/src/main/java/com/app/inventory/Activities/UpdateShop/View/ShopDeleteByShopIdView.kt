package com.app.inventory.Activities.UpdateShop.View

import android.app.Activity

interface ShopDeleteByShopIdView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}