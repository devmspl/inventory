package com.app.inventory.Fragments.LoginFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.app.inventory.Api.WebServices
import com.app.inventory.Fragments.LoginFragment.LoginFragment
import com.app.inventory.Fragments.LoginFragment.View.LoginView
import com.app.inventory.Handler.ForgotPasswordHandler
import com.app.inventory.Handler.LoginHandler
import com.app.inventory.Models.ForgotPasseword.ForgotPassExample
import com.app.inventory.Models.LoginExample
import com.app.inventory.R
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils

class LoginPresenter(
    private val activity: FragmentActivity,
    private val loginView: LoginView,
    private val loginFragment: LoginFragment,
) {
    private var et_pass: String? = null
    private var et_mail: String? = null

    fun loginMethod(et_mail: String, et_pass: String) {
        this.et_mail = et_mail
        this.et_pass = et_pass
        var token: String? = null
        token = CSPreferences.readString(activity, Utils.FCMTOKEN)
        if (Utils.isNetworkConnected(activity!!)) {
            if (checkValidation())
                loginView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.loginMethod(et_mail, et_pass, token, object :
                LoginHandler {
                override fun onSuccess(loginExample: LoginExample?, acesstoken: String?) {

                    loginView?.hideDialog()
                    if (loginExample != null) {
                        if (loginExample.isSuccess === true) {
                            findNavController(loginFragment).navigate(R.id.action_loginFragment_to_roleFragment)
                            CSPreferences.putString(activity, Utils.USERLOGIN,"true")
                            CSPreferences.putString(activity!!, Utils.TOKEN,acesstoken!!)
                            CSPreferences.putString(activity, Utils.USERID,loginExample.data.id)
                            loginView?.showMessage(activity, loginExample.message)
                        } else {
                            loginView?.showMessage(activity, loginExample.message)
                        }
                    } else {
                        loginView?.showMessage(activity, loginExample?.message)
                    }
                }

                override fun onError(message: String?) {
                    loginView?.hideDialog()
                    loginView?.showMessage(activity, message)
                }
            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }

    fun forgotpassMethod(et_mail: String) {
        if (Utils.isNetworkConnected(activity!!)) {
            loginView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.forgotpassMethod(et_mail, object :
                ForgotPasswordHandler {
                override fun onSuccess(forgotPassExample: ForgotPassExample?) {
                    loginView?.hideDialog()
                    if (forgotPassExample != null) {
                        if (forgotPassExample.isSuccess === true) {
                            CSPreferences.putString(
                                activity, Utils.FORGOTPASSWORDTOKEN,"true")
                            CSPreferences.putString(activity, Utils.FORGOTPASSMAIL,et_mail)
                            findNavController(loginFragment).navigate(R.id.action_loginFragment_to_otpFragment)

                        } else {
                            loginView?.showMessage(activity, forgotPassExample.message.message)
                        }
                    } else {
                        loginView?.showMessage(activity, forgotPassExample?.message?.message)
                    }
                }

                override fun onError(message: String?) {
                    loginView?.hideDialog()
                    loginView?.showMessage(activity, message)
                }
            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }

    private fun checkValidation(): Boolean {
        if (et_mail?.length == 0) {
            return false
        } else if (et_pass?.length == 0) {
            return false
        }
        return true
    }
}