package com.app.inventory.Fragments.SearchFragment

import SearchFFProductPresenter
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.app.inventory.Activities.SearchProductActivity.Presenter.SearchProductPresenter
import com.app.inventory.Activities.SearchProductActivity.View.SearchProductView
import com.app.inventory.Adapter.SearchProductAdapter
import com.app.inventory.Fragments.SearchFragment.View.SearchFFProductView
import com.app.inventory.R
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.FragmentSearchproductBinding

class SearchProductFragment : Fragment(), SearchFFProductView {
    private lateinit var binding: FragmentSearchproductBinding
    private var searchFFProductPresenter: SearchFFProductPresenter? = null
    private var searchProductAdapter: SearchProductAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSearchproductBinding.inflate(inflater, container, false)
        val view: View = binding.root


        val arguments = arguments
        if (arguments != null) {
            val shopId = arguments.getString("shopid")
            val   Search = arguments?.getString("search")
        }



        binding?.grdiview?.adapter = searchProductAdapter
        searchFFProductPresenter = SearchFFProductPresenter(activity as FragmentActivity, this, this, binding?.grdiview,arguments)
        searchFFProductPresenter!!.searchProduct()

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