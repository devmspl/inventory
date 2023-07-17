package com.app.inventory.Fragments.HomeFragment.View

import android.app.Activity
import androidx.fragment.app.FragmentActivity

interface HomeView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
 //   fun getdata(activity: FragmentActivity, data: Data) {

    }
