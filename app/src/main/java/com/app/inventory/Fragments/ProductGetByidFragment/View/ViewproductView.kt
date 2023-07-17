package com.app.inventory.Fragments.ProductGetByidFragment.View

import android.app.Activity

interface ViewproductView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}