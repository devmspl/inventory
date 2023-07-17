package com.app.inventory.Activities.ProductDetailsActivity.Presenter

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.app.inventory.Activities.ProductDetailsActivity.ProductDetailsActivity
import com.app.inventory.Activities.ProductDetailsActivity.View.DeleteProductView
import com.app.inventory.Api.WebServices
import com.app.inventory.Fragments.ProductGetByidFragment.ProductGetByIdFragment
import com.app.inventory.Fragments.ShopGetByUserIdFragment.ShopgetByUserIdFragment
import com.app.inventory.Handler.DeleteProductHandler
import com.app.inventory.Models.DeleteProductModel.DeleteProductExample
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils

class DeleteProductPresenter(
    private val deleteProductView: DeleteProductView,
    private val activity: Activity, private val productDetailsActivity: ProductDetailsActivity,
) {
    fun deleteProduct() {
        if (Utils.isNetworkConnected(activity!!)) {
            val token = CSPreferences.readString(activity, Utils.TOKEN)
            val id = productDetailsActivity.id
            deleteProductView?.showDialog(activity)
            WebServices.Factory1.getInstance()
                ?.deleteProduct(token!!, id!!, object : DeleteProductHandler {
                    override fun onSuccess(deleteProductExample: DeleteProductExample) {
                        deleteProductView?.hideDialog()
                        if (deleteProductExample != null) {
                            if (deleteProductExample.isSuccess == true) {
                                if (deleteProductExample.data.isNotEmpty()) {
                                    Toast.makeText(
                                        activity, "Delete Product", Toast.LENGTH_SHORT
                                    ).show()
                                    val intent =
                                        Intent(activity, ProductGetByIdFragment::class.java)
                                    activity.startActivity(intent)

                                } else {
                                    deleteProductView?.showMessage(
                                        activity, deleteProductExample.message
                                    )

                                }
                            } else {
                            }
                        } else {
                        }
                    }

                    override fun onError(message: String?) {
                        deleteProductView?.hideDialog()
                        deleteProductView?.showMessage(activity, message)
                    }
                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
        }
    }
}