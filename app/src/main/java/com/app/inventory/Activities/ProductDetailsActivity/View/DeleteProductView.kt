package com.app.inventory.Activities.ProductDetailsActivity.View

import android.app.Activity

interface DeleteProductView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}