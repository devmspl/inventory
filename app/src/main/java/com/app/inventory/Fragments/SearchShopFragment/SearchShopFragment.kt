package com.app.inventory.Fragments.SearchShopFragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.app.inventory.Adapter.SearchProductAdapter
import com.app.inventory.Adapter.SearchShopAdapter
import com.app.inventory.Fragments.SearchShopFragment.Presenter.SearchShopPresenter
import com.app.inventory.Fragments.SearchShopFragment.View.SearchShopView
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.FragmentSearchShopBinding

class SearchShopFragment : Fragment(),SearchShopView {
    private var binding: FragmentSearchShopBinding? = null
    private var searchShopPresenter: SearchShopPresenter? = null
    private var searchShopAdapter: SearchShopAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSearchShopBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        val arguments = arguments
        if (arguments != null) {
           // val token = arguments.getString("token")
            val   Search = arguments?.getString("searchShop")
        }



        binding?.grdiView?.adapter = searchShopAdapter
        searchShopPresenter = SearchShopPresenter(activity as FragmentActivity, this, this, binding?.grdiView,arguments)
        searchShopPresenter!!.searchShop()

        return view

    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity, msg)
    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity, activity!!.fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }


}