package com.app.inventory.Fragments.ProductGetByShopIdFragment.Presenter

import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.inventory.Adapter.ProductGetByShopIdAdapter
import com.app.inventory.Api.WebServices
import com.app.inventory.Fragments.ProductGetByShopIdFragment.ProductGetByShopIdFragment
import com.app.inventory.Fragments.ProductGetByShopIdFragment.View.GetProductByShopIdView
import com.app.inventory.Handler.ProductGetByShopIdHandler
import com.app.inventory.Models.ProductGetByShopid.ProductGetByShopIdExample
import com.app.inventory.Utlis.Utils

class ProductGetByShopIdPresenter(
    private val activity: FragmentActivity,
    private val getProductByShopIdView: GetProductByShopIdView,
    private val getProductByShopIdFragment: ProductGetByShopIdFragment,
    private val gridView: GridView?,
    private val arguments: Bundle?
) {

    private var productGetByShopIdAdapter: ProductGetByShopIdAdapter? = null
    fun getProductByShopIdById() {
        val arguments = arguments
            val id = arguments?.getString("shopid")
        if (Utils.isNetworkConnected(activity!!)) {

            getProductByShopIdView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.productgetByShopId(id!!, object :
                ProductGetByShopIdHandler {
                override fun onSuccess(productGetByShopIdExample: ProductGetByShopIdExample?) {
                    getProductByShopIdView.hideDialog()
                    if (productGetByShopIdExample != null) {
                        if (productGetByShopIdExample.isSuccess) {
                            if (productGetByShopIdExample.data.isNotEmpty()) {
                                productGetByShopIdAdapter = ProductGetByShopIdAdapter(
                                    activity,
                                    getProductByShopIdFragment, productGetByShopIdExample.data

                                )
                                gridView?.adapter = productGetByShopIdAdapter
                            } else {
                                Toast.makeText(activity, "No Product Available", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } }}

                override fun onError(message: String?) {
                    getProductByShopIdView?.hideDialog()
                    getProductByShopIdView?.showMessage(activity, message)                }


            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }
    }
}