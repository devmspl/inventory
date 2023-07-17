package com.app.inventory.Fragments.ShopGetByUserIdFragment.View

import android.app.Activity

interface ShopGetByUserIdView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}