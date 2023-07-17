package com.app.inventory.Fragments.HomeFragment.Presenter

import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.inventory.Adapter.GetAllShopAdapter
import com.app.inventory.Api.WebServices
import com.app.inventory.Fragments.HomeFragment.HomeFragment
import com.app.inventory.Fragments.HomeFragment.View.HomeView
import com.app.inventory.Handler.GetAllShopHandler
import com.app.inventory.Models.GetAllShopModel.GetAllShopModel
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils

class HomePresenter(
    private val activity: FragmentActivity,
    private val homeView: HomeView,
    private val homeFragment: HomeFragment,
    private val gridView: GridView?,
) {
    private var gridViewAdapter: GetAllShopAdapter? = null

    fun getAllShops() {
        if (Utils.isNetworkConnected(activity!!)) {
            val token = CSPreferences.readString(activity, Utils.TOKEN)
            homeView.showDialog(activity)
            WebServices.Factory1.getInstance()?.getAllShop(token!!, 1, 100, object :
                GetAllShopHandler {
                override fun onSuccess(getAllShopModel: GetAllShopModel?) {
                    homeView.hideDialog()
                    if (getAllShopModel != null) {
                        if (getAllShopModel.isSuccess == true) {
                            if (getAllShopModel.items.isNotEmpty()) {
                                gridViewAdapter = GetAllShopAdapter(
                                    activity,
                                    homeFragment,
                                    getAllShopModel.items
                                )
                                gridView?.adapter = gridViewAdapter
                            } else {
                                Toast.makeText(activity, "No shops available", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } else {
                            Toast.makeText(activity, "adffgghhk", Toast.LENGTH_SHORT).show()
                        }
                    } else {Toast.makeText(activity, "gfhgghjghgh", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(message: String?) {
                    homeView.hideDialog()
                    homeView.showMessage(activity, message)
                }
            })

        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
        }
    }
}