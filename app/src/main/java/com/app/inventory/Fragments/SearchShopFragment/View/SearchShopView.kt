package com.app.inventory.Fragments.SearchShopFragment.View

import android.app.Activity

interface SearchShopView {
    fun showMessage(activity: Activity?, msg: String?,)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}