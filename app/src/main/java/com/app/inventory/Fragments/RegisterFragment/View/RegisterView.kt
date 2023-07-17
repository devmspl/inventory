package com.app.inventory.Fragments.RegisterFragment.View

import android.app.Activity

interface RegisterView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()

}