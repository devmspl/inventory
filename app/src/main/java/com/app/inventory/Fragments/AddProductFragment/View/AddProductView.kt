package com.app.inventory.Fragments.AddProductFragment.View

import android.app.Activity

interface AddProductView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}