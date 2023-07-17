package com.app.inventory.Fragments.RoleFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.app.inventory.Api.WebServices
import com.app.inventory.Fragments.RoleFragment.RoleFragment
import com.app.inventory.Fragments.RoleFragment.View.RoleView
import com.app.inventory.Handler.RoleHandler
import com.app.inventory.Models.RoleModel.RoleExample
import com.app.inventory.R
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils

class RolePresenter(
    private val activity: FragmentActivity,
    private val roleView: RoleView,
    private val roleFragment: RoleFragment,
) {
    fun shopGetByUser() {
        if (Utils.isNetworkConnected(activity!!)) {
            val id = CSPreferences.readString(activity, Utils.USERID)
            roleView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.shopGetByUserMethod(id!!, object :
                RoleHandler {
                override fun onSuccess(roleExample: RoleExample?) {
                    roleView.hideDialog()
                    if (roleExample != null) {
                        if (roleExample.isSuccess == true) {
                            if (roleExample.data.isNotEmpty()) {
                                val data = roleExample.data[0]
                                CSPreferences.putString(activity, Utils.SID, data._id)
                                CSPreferences.putString(activity, Utils.SADDRES, data.address)
                                CSPreferences.putString(
                                    activity, Utils.SNO,
                                    data.phoneno.toString()
                                )
                                CSPreferences.putString(activity, Utils.SNAME, data.name)
                                CSPreferences.putString(activity, Utils.SAVATAR, data.avatar)
                                CSPreferences.putString(activity, Utils.SEMAIL, data.email)

                                NavHostFragment.findNavController(roleFragment)
                                    .navigate(R.id.action_roleFragment_to_homeNavActivity)


                            } else {

                                NavHostFragment.findNavController(roleFragment)
                                    .navigate(R.id.action_roleFragment_to_addShopFragement2)
                            }
                        } else {
                        }
                    }
                }

                override fun onError(message: String?) {
                    roleView?.hideDialog()
                    roleView?.showMessage(activity, message)
                }
            })
        } else {
            Toast.makeText(
                activity,
                "Please check internet connection",
                Toast.LENGTH_SHORT
            ).show()

        }
    }
}