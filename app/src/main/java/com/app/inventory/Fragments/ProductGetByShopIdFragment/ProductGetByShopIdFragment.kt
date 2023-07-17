package com.app.inventory.Fragments.ProductGetByShopIdFragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.app.inventory.Adapter.ProductGetByShopIdAdapter
import com.app.inventory.Fragments.ProductGetByShopIdFragment.Presenter.ProductGetByShopIdPresenter
import com.app.inventory.Fragments.ProductGetByShopIdFragment.View.GetProductByShopIdView
import com.app.inventory.Fragments.SearchFragment.SearchProductFragment
import com.app.inventory.R
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.FragmentGetProductByShopIdBinding

class ProductGetByShopIdFragment : Fragment(), GetProductByShopIdView,
    TextView.OnEditorActionListener, View.OnClickListener {
    private var binding: FragmentGetProductByShopIdBinding? = null
    private var getProductByShopIdPresenter: ProductGetByShopIdPresenter? = null
    private val getProductByShopIdAdapter: ProductGetByShopIdAdapter? = null
    private var shopId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentGetProductByShopIdBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        val arguments = arguments
        if (arguments != null) {
             shopId = arguments.getString("shopid")
        }

        listeners()

        binding?.grdiview?.adapter = getProductByShopIdAdapter
        getProductByShopIdPresenter =
            ProductGetByShopIdPresenter(activity as FragmentActivity, this, this, binding?.grdiview,arguments)
        getProductByShopIdPresenter?.getProductByShopIdById()
        return view
    }

    private fun listeners() {
        binding!!.searchproduct.setOnEditorActionListener(this)
        binding?.backActivity?.setOnClickListener(this)
    }

    override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
        if (p1 == EditorInfo.IME_ACTION_SEARCH) {
            if (!(binding!!.searchproduct?.text?.length == 0)) {
                val bundle = Bundle()
                bundle.putString("search", binding!!.searchproduct?.text.toString())
                bundle.putString("shopid", shopId)
                val searchProductFragment = SearchProductFragment()
                searchProductFragment.arguments = bundle
                findNavController().navigate(R.id.action_getProductByShopIdFragment_to_searchproductFragment, bundle)

                performsearchpp()
                return true
            } else {
                Toast.makeText(activity, "Please enter text", Toast.LENGTH_SHORT).show()
            }

        }
        return false
    }

    private fun performsearchpp() {
        binding?.searchproduct!!.clearFocus()
        val `in`: InputMethodManager =
            requireActivity()!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        `in`.hideSoftInputFromWindow(binding?.searchproduct!!?.getWindowToken(), 0)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.imgadd -> {
                //  findNavController().navigate(R.id.action_allProductFragment_to_addProductFragment)
            }

            R.id.backActivity -> {
                findNavController().navigate(R.id.action_getProductByShopIdFragment_to_homeFragment2)
            }

        }
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