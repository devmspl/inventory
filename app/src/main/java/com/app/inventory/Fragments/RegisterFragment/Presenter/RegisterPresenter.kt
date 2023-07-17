package com.app.inventory.Fragments.RegisterFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.app.inventory.Api.WebServices
import com.app.inventory.Fragments.RegisterFragment.RegisterFragment
import com.app.inventory.Fragments.RegisterFragment.View.RegisterView
import com.app.inventory.Handler.SignupHandler
import com.app.inventory.Models.SignUp.SignupExample
import com.app.inventory.R
import com.app.inventory.Utlis.Utils

class RegisterPresenter(
    private val activity: FragmentActivity,
    private val registerFragment: RegisterFragment,
    private val registerView: RegisterView,
) {
    fun signupMethod(name: String, email: String, phoneNo: String, password: String) {
        val devicetoken = "devicetoken"
        if (Utils.isNetworkConnected(activity!!)) {
            registerView?.showDialog(activity)
            WebServices.Factory1.getInstance()
                ?.signupMethod(name, email, phoneNo, password, devicetoken!!, object :
                    SignupHandler {
                    override fun onSuccess(signupExample: SignupExample?, deviceToken: String?) {
                        registerView?.hideDialog()
                        if (signupExample != null) {
                            if (signupExample.isSuccess === true) {
                              //  CSPreferences.putString(activity, Utils.USERLOGIN, "true")
                                findNavController(registerFragment).navigate(R.id.action_registerFragment_to_loginFragment)
                                registerView?.showMessage(activity, signupExample.message)

                            } else {
                                registerView?.showMessage(activity, signupExample.message)
                            }
                        } else {
                            registerView?.showMessage(activity, signupExample?.message)
                        }
                    }

                    override fun onError(message: String?) {
                        registerView?.hideDialog()
                        registerView?.showMessage(activity, message)
                    }
                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }

}