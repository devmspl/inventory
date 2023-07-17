package com.app.inventory.Fragments.AllProductFragment.Presenter


import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.inventory.Adapter.GetAllProductAdapter
import com.app.inventory.Api.WebServices
import com.app.inventory.Fragments.AllProductFragment.AllProductFragment
import com.app.inventory.Fragments.AllProductFragment.View.AllProductView
import com.app.inventory.Handler.GetAllProductHandler
import com.app.inventory.Models.GetAllProductModel.GetAllProductModel
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils

class AllProductPresenter(
    private val activity: FragmentActivity,
    private val allProductView: AllProductView,
    private val allProductFragment: AllProductFragment,
    private val gridView: GridView?,
) {
    private var getAllProductAdapter: GetAllProductAdapter? = null

    fun getAllProduct() {
        if (Utils.isNetworkConnected(activity!!)) {
            val token = CSPreferences.readString(activity, Utils.TOKEN)
            allProductView.showDialog(activity)
            WebServices.Factory1.getInstance()?.getAllproduct(token!!, 1, 100, object :
                GetAllProductHandler {
                override fun onSuccess(getAllProductModel: GetAllProductModel?) {
                    allProductView.hideDialog()
                    if (getAllProductModel != null) {

                        if (getAllProductModel.isSuccess == true) {
                            if (getAllProductModel.items.isNotEmpty()) {
                                getAllProductAdapter = GetAllProductAdapter(
                                    activity,
                                    allProductFragment,
                                    getAllProductModel.items
                                )
                                gridView?.adapter = getAllProductAdapter
                            } else {
                                Toast.makeText(activity, "No shops available", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } else {
                        }
                    } else {
                    }
                }
                override fun onError(message: String?) {
                    allProductView.hideDialog()
                    allProductView.showMessage(activity, message)
                }
            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
        }
    }

}