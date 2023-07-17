package com.app.inventory.Activities.SearchProductActivity.View

import android.app.Activity

interface SearchProductView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}