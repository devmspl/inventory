package com.app.inventory.Fragments.HomeFragment

import android.app.Activity
import android.content.Context
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
import com.app.inventory.Adapter.GetAllShopAdapter
import com.app.inventory.Fragments.HomeFragment.Presenter.HomePresenter
import com.app.inventory.Fragments.HomeFragment.View.HomeView
import com.app.inventory.Fragments.SearchShopFragment.SearchShopFragment
import com.app.inventory.R
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), TextView.OnEditorActionListener,HomeView {
    private var binding: FragmentHomeBinding? = null
    private var getAllShopAdapter: GetAllShopAdapter? = null
    private var homePresenter: HomePresenter? = null
    var token: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view: View = binding!!.root

        token = CSPreferences.readString(activity!!, Utils.TOKEN)
        listeners()
        binding?.grdiview?.adapter = getAllShopAdapter
        homePresenter = HomePresenter(activity as FragmentActivity, this, this, binding?.grdiview)
        homePresenter?.getAllShops()
        return view
    }

    private fun listeners() {
        binding?.etSearchShop?.setOnEditorActionListener(this)
    }

    override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
        if (p1 == EditorInfo.IME_ACTION_SEARCH) {
            if (!(binding!!.etSearchShop?.text?.length == 0)) {
                val bundle = Bundle()
                bundle.putString("searchShop", binding!!.etSearchShop?.text.toString())
                val searchShopFragment = SearchShopFragment()
                searchShopFragment.arguments = bundle
                findNavController().navigate(
                    R.id.action_homeFragment2_to_searchShopFragment,
                    bundle
                )

                performsearch()
                return true
            } else {
                Toast.makeText(activity, "Please enter text", Toast.LENGTH_SHORT).show()
            }

        }
        return false
    }

    private fun performsearch() {
        binding?.etSearchShop!!.clearFocus()
        val `in`: InputMethodManager =
            requireActivity()!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        `in`.hideSoftInputFromWindow(binding?.etSearchShop!!?.getWindowToken(), 0)
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