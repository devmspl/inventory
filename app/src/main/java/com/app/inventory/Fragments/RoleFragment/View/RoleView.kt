package com.app.inventory.Fragments.RoleFragment.View

import android.app.Activity

interface RoleView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}