package com.app.inventory.Fragments.ChangePasswordFragment.View

import android.app.Activity

interface ChangePassView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}