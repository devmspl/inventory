package com.app.inventory.Fragments.AllProductFragment.View

import android.app.Activity


interface AllProductView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}
