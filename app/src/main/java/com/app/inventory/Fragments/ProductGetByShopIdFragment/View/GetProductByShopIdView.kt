package com.app.inventory.Fragments.ProductGetByShopIdFragment.View

import android.app.Activity

interface GetProductByShopIdView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}