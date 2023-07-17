package com.app.inventory.Activities.SearchProductActivity.Presenter

import android.app.Activity
import android.widget.GridView
import android.widget.Toast
import com.app.inventory.Activities.SearchProductActivity.SearchProductActivity
import com.app.inventory.Activities.SearchProductActivity.View.SearchProductView
import com.app.inventory.Adapter.SearchProductResultAdapter
import com.app.inventory.Api.WebServices
import com.app.inventory.Handler.GetProductBySearchHandler
import com.app.inventory.Models.ProductSearchByShopIdModel.ProductSearchByShopId
import com.app.inventory.Utlis.Utils

class SearchProductPresenter(
    private val activity: Activity,
    private val searchProductView: SearchProductView,
    private val searchProductActivity: SearchProductActivity,
    private val gridView: GridView?,
) {
    private var searchProductResultAdapter: SearchProductResultAdapter? = null

    fun searchProductResult() {
        if (Utils.isNetworkConnected(activity!!)) {
            val shopId = searchProductActivity.shopId
           val searchValue=searchProductActivity.Search
            searchProductView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.productSearchByShopIdMethod(
                shopId!!, searchValue!!, "", "",

                object : GetProductBySearchHandler {
                    override fun onSuccess(productSearchByShopId: ProductSearchByShopId?) {
                        searchProductView?.hideDialog()
                        if (productSearchByShopId != null) {
                            if (productSearchByShopId.isSuccess === true) {
                                if (productSearchByShopId.data.isNotEmpty()==true){
                                val filteredProducts = productSearchByShopId.data.filter {
                                    it.productname.contains(searchValue, ignoreCase = true)
                                }
                                searchProductResultAdapter = SearchProductResultAdapter(
                                    activity, searchProductActivity, filteredProducts
                                )
                                gridView?.adapter = searchProductResultAdapter
                                //searchProductView?.showMessage(activity, getProductBySearchExample.message)
                            } else {
                                //   searchProductView?.showMessage(activity, getProductBySearchExample.message)
                            }
                        } else {
                            //  searchProductView?.showMessage(activity, getProductBySearchExample?.message)
                        }
                    }}

                    override fun onError(message: String?) {
                        searchProductView?.hideDialog()
                        searchProductView?.showMessage(activity, message)
                    }
                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }
}