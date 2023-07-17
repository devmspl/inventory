package com.app.inventory.Fragments.OtpFragment.View

import android.app.Activity

interface OtpVerificationView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}