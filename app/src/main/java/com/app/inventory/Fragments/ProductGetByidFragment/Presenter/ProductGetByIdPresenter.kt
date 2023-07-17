package com.app.inventory.Fragments.ProductGetByidFragment.Presenter

import ProductGetByIdAdapter
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.inventory.Api.WebServices
import com.app.inventory.Fragments.ProductGetByidFragment.ProductGetByIdFragment
import com.app.inventory.Fragments.ProductGetByidFragment.View.ViewproductView
import com.app.inventory.Handler.ProductGetByIdHandler
import com.app.inventory.Models.ProductGetById.ProductGetByIdExample
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils

class ProductGetByIdPresenter(
    private val activity: FragmentActivity,
    private val viewproductView: ViewproductView,
    private val productGetByIdFragment: ProductGetByIdFragment,
    private val gridView: GridView?,
) {
    private var productGetByIdAdapter: ProductGetByIdAdapter? = null
    fun productGetById() {
        if (Utils.isNetworkConnected(activity!!)) {
            val id = CSPreferences.readString(activity, Utils.SID)
            viewproductView.showDialog(activity)
            WebServices.Factory1.getInstance()?.productGetById(id!!, object :
                ProductGetByIdHandler {
                override fun onSuccess(productGetByIdExample: ProductGetByIdExample?) {
                    viewproductView.hideDialog()
                    if (productGetByIdExample != null) {
                        if (productGetByIdExample.isSuccess) {
                            if (productGetByIdExample.data.isNotEmpty()==true)
                                productGetByIdAdapter = ProductGetByIdAdapter(
                                    activity, productGetByIdFragment, productGetByIdExample.data)
                            gridView?.adapter = productGetByIdAdapter
                        } else {
                            Toast.makeText(
                                activity,
                                "No Products Available",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            activity,
                            "Failed to retrieve products",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onError(message: String?) {
                    viewproductView.hideDialog()
                    viewproductView.showMessage(activity, message)
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
