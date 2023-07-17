package com.app.inventory.Fragments.SearchFragment.View

import android.app.Activity
import com.app.inventory.Models.SearchproductModel.SearchproductExample

interface SearchFFProductView {
    fun showMessage(activity: Activity?, msg: String?,)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}