package com.app.inventory.Activities.UpdateShop.Presenter

import android.app.Activity
import android.widget.Toast
import com.app.inventory.Activities.UpdateShop.ShopDetailsActivity
import com.app.inventory.Activities.UpdateShop.View.ShopDeleteByShopIdView
import com.app.inventory.Api.WebServices
import com.app.inventory.Fragments.AddShopFragement.AddShopFragement
import com.app.inventory.Handler.ShopDeleteByShopIdHandler
import com.app.inventory.Models.ShopDeleteByShopIdModel.ShopDeleteByShopIdExample
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils

class DeleteShopPresente(
    private val activity: Activity,
    private val shopDeleteByShopIdView: ShopDeleteByShopIdView,
    private val shopDetailsActivity: ShopDetailsActivity,
) {

    fun shopDeleteByShopId() {
        if (Utils.isNetworkConnected(activity!!)) {
            val id = CSPreferences.readString(activity!!, Utils.SID)
            val token = CSPreferences.readString(activity, Utils.TOKEN)
            shopDeleteByShopIdView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.shopDeleteByShopIdMethod(
                token!!, id!!, object : ShopDeleteByShopIdHandler {
                    override fun onSuccess(shopDeleteByShopIdExample: ShopDeleteByShopIdExample?) {
                        shopDeleteByShopIdView?.hideDialog()
                        if (shopDeleteByShopIdExample != null) {
                            if (shopDeleteByShopIdExample.isSuccess == true) {
                               CSPreferences.clear(activity,id)
                              // CSPreferences.clear(activity,token)
                                Utils.moveToFragment(activity, AddShopFragement())
                                shopDeleteByShopIdView?.showMessage(
                                    activity,
                                    shopDeleteByShopIdExample.message
                                )
                            } else {
                                shopDeleteByShopIdView?.showMessage(
                                    activity,
                                    shopDeleteByShopIdExample.data
                                )
                            }
                        } else {
                            shopDeleteByShopIdView?.showMessage(
                                activity,
                                shopDeleteByShopIdExample?.data
                            )
                        }
                    }

                    override fun onError(message: String?) {
                        shopDeleteByShopIdView?.hideDialog()
                        shopDeleteByShopIdView?.showMessage(activity, message)
                    }
                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
        }
    }
}