package com.app.inventory.Fragments.ProductGetByidFragment

import ProductGetByIdAdapter
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import com.app.inventory.Activities.SearchProductActivity.SearchProductActivity
import com.app.inventory.Fragments.ProductGetByidFragment.Presenter.ProductGetByIdPresenter
import com.app.inventory.Fragments.ProductGetByidFragment.View.ViewproductView
import com.app.inventory.R
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.FragmentProductgetbyidBinding


class ProductGetByIdFragment : Fragment(), ViewproductView, TextView.OnEditorActionListener,
    View.OnClickListener {
    private var binding:FragmentProductgetbyidBinding? = null
        private var productGetByIdPresenter: ProductGetByIdPresenter? = null
        private var productGetByIdAdapter: ProductGetByIdAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProductgetbyidBinding.inflate(inflater, container, false)
        val view: View = binding!!.root


        listeners()

        binding?.grdiview?.adapter = productGetByIdAdapter
        productGetByIdPresenter = ProductGetByIdPresenter(activity as FragmentActivity, this, this, binding?.grdiview)
        productGetByIdPresenter?.productGetById()
        return view
    }

    private fun listeners() {
        binding!!.etsearchproduct.setOnEditorActionListener(this)
        binding!!.imgadd.setOnClickListener(this)
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity, msg)

    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity, activity?.fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()

    }

    override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
        if (p1 == EditorInfo.IME_ACTION_SEARCH) {
            if (!(binding!!.etsearchproduct?.text?.length == 0)) {
                var intent = Intent(activity, SearchProductActivity::class.java)
                intent.putExtra("searchh", binding!!.etsearchproduct?.text.toString())
                startActivity(intent)
               // productGetByIdPresenter?.searchjobs(et_search?.text.toString())
                performsearch()
                return true
            } else {
                Toast.makeText(activity, "Please enter text", Toast.LENGTH_SHORT).show()
            }

        }
        return false
    }

    private fun performsearch() {
        binding?.etsearchproduct!!.clearFocus()
        val `in`: InputMethodManager =
            requireActivity()!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        `in`.hideSoftInputFromWindow(binding?.etsearchproduct!!?.getWindowToken(), 0)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.imgadd -> {
                findNavController().navigate(R.id.action_productGetByIdFragment_to_addProductFragment)
            }

        }
    }


}