package com.app.inventory.Fragments.SearchShopFragment.Presenter

import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.inventory.Adapter.SearchShopAdapter
import com.app.inventory.Api.WebServices
import com.app.inventory.Fragments.SearchShopFragment.SearchShopFragment
import com.app.inventory.Fragments.SearchShopFragment.View.SearchShopView
import com.app.inventory.Handler.SearchShopHandler
import com.app.inventory.Models.SearchShopModel.SearchShopExample
import com.app.inventory.Utlis.Utils

class SearchShopPresenter(
    private val activity: FragmentActivity,
    private val searchShopView: SearchShopView,
    private val searchShopFragment: SearchShopFragment,
    private val gridView: GridView?,
    private val arguments: Bundle?
) {
    private var searchShopAdapter: SearchShopAdapter? = null

    fun searchShop() {
        if (Utils.isNetworkConnected(activity!!)) {
            val searchValue = arguments?.getString("searchShop")
            searchShopView.showDialog(activity)
            WebServices.Factory1.getInstance()?.searchShop(
                searchValue!!, "100", "1", object : SearchShopHandler {
                    override fun onSuccess(searchShopExample: SearchShopExample) {
                        searchShopView.hideDialog()
                        if (searchShopExample != null) {
                            if (searchShopExample.isSuccess == true) {
                                if (searchShopExample.data.isNotEmpty()) {
                                    val filteredShops = searchShopExample.data.filter {
                                        it.name.contains(searchValue, ignoreCase = true)
                                    }
                                    searchShopAdapter = SearchShopAdapter(
                                        activity,
                                        searchShopFragment,
                                        filteredShops
                                    )
                                    gridView?.adapter = searchShopAdapter
                                } else {
                                    Toast.makeText(activity, "No Shop", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                // searchProductView?.showMessage(activity, getProductBySearchExample.message)
                            }
                        } else {
                            // searchProductView?.showMessage(activity, getProductBySearchExample?.message)
                        }
                    }

                    override fun onError(message: String?) {
                        searchShopView.hideDialog()
                        searchShopView.showMessage(activity, message)
                    }
                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
        }
    }
}