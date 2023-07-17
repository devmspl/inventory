package com.app.inventory.Fragments.OtpFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.app.inventory.Fragments.OtpFragment.OtpFragment
import com.app.inventory.Fragments.OtpFragment.View.OtpVerificationView
import com.app.inventory.Handler.OtpVerificationHandler
import com.app.inventory.Models.OtpVerification.OtpExample
import com.app.inventory.R
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import com.app.inventory.Api.WebServices

class OtpVerificationPresenter(
    private val activity: FragmentActivity,
    private val otpVerificationView: OtpVerificationView,
   private val otpFragment: OtpFragment
) {

    fun forgotpassMethod(otp: String) {
        var otpToken:String?=null
        otpToken = CSPreferences.readString(activity,Utils.FORGOTPASSWORDTOKEN)
        if (Utils.isNetworkConnected(activity!!)) {
            otpVerificationView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.otpVerificationMethod(otp, otpToken!!, object :
                OtpVerificationHandler {

                override fun onSuccess(otpExample: OtpExample?) {
                    otpVerificationView?.hideDialog()
                    if (otpExample != null) {
                        if (otpExample.isSuccess === true) {
                            NavHostFragment.findNavController(otpFragment).navigate(R.id.action_otpFragment_to_changePasswordFragment2 )

//                            movetoActivity(activity, LoginActivity())

                        } else {
                            otpVerificationView?.showMessage(activity, otpExample.message.message)
                        }
                    } else {
                        otpVerificationView?.showMessage(activity, otpExample?.message?.message)
                    }
                }

                override fun onError(message: String?) {
                    otpVerificationView?.hideDialog()
                    otpVerificationView?.showMessage(activity, message)
                }
            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }
}