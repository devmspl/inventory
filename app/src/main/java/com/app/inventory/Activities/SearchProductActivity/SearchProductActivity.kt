package com.app.inventory.Activities.SearchProductActivity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.inventory.Activities.SearchProductActivity.Presenter.SearchProductPresenter
import com.app.inventory.Activities.SearchProductActivity.View.SearchProductView
import com.app.inventory.Adapter.SearchProductResultAdapter
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.ActivitySearchProductBinding

class SearchProductActivity : AppCompatActivity(), SearchProductView {
    private lateinit var binding: ActivitySearchProductBinding
    private var searchProductPresenter: SearchProductPresenter? = null
    private var searchProductResultAdapter: SearchProductResultAdapter? = null
    var Search: String? = null
    var shopId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listeners()
        shopId=CSPreferences.readString(this,Utils.SHOPID)
        Search = intent.getStringExtra("searchh")

        binding?.grdiview?.adapter = searchProductResultAdapter
        searchProductPresenter = SearchProductPresenter(this, this,this ,binding?.grdiview)
        searchProductPresenter!!.searchProductResult()
    }

    private fun listeners() {

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