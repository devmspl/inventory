package com.app.inventory.Fragments.ChangePasswordFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.app.inventory.Fragments.ChangePasswordFragment.ChangePasswordFragment
import com.app.inventory.Fragments.ChangePasswordFragment.View.ChangePassView
import com.app.inventory.Handler.ForgotChangePassHandler
import com.app.inventory.Models.ForgotChangePassword.ForgotChangePassExample
import com.app.inventory.R
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import com.app.inventory.Api.WebServices

class ChangePassPresenter(
   private val activity: FragmentActivity,
   private val changePassView: ChangePassView,
   private val changePasswordFragment: ChangePasswordFragment
) {
   fun changePasswordMethod(pass: String) {
      var token = CSPreferences.readString(activity,Utils.FORGOTPASSWORDTOKEN)
      var  email = CSPreferences.readString(activity,Utils.FORGOTPASSMAIL)

      if (Utils.isNetworkConnected(activity!!)) {
         changePassView?.showDialog(activity)
         WebServices.Factory1.getInstance()?.changePasswordMethod(token!!,email!!,pass , object :
            ForgotChangePassHandler {
            override fun onSuccess(forgotPassExample: ForgotChangePassExample?) {
               changePassView?.hideDialog()
               if (forgotPassExample != null) {
                  if (forgotPassExample.isSuccess === true) {

                     NavHostFragment.findNavController(changePasswordFragment).navigate(R.id.action_changePasswordFragment2_to_loginFragment)

                  } else {
                     changePassView?.showMessage(activity, forgotPassExample.message)
                  }
               } else {
                  changePassView?.showMessage(activity, forgotPassExample?.message)
               }
            }

            override fun onError(message: String?) {
               changePassView?.hideDialog()
               changePassView?.showMessage(activity, message)
            }
         })
      } else {
         Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

      }

   }

}