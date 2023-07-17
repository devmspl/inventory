package com.app.inventory.Fragments.AddShopFragement.View

import android.app.Activity

interface AddShopView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}