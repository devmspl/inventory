package com.app.inventory.Fragments.AllProductFragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.app.inventory.Adapter.GetAllProductAdapter
import com.app.inventory.Fragments.AllProductFragment.Presenter.AllProductPresenter
import com.app.inventory.Fragments.AllProductFragment.View.AllProductView
import com.app.inventory.Models.GetAllProductModel.Item
import com.app.inventory.R
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.FragmentAllProductBinding

class AllProductFragment : Fragment(), View.OnClickListener, AllProductView {
    private var binding: FragmentAllProductBinding? = null
    private var getAllProductAdapter: GetAllProductAdapter? = null
    private var allProductPresenter: AllProductPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAllProductBinding.inflate(inflater, container, false)
            val view: View = binding!!.root
        listeners()

        binding?.grdiview?.adapter = getAllProductAdapter
        allProductPresenter = AllProductPresenter(activity as FragmentActivity, this, this, binding?.grdiview)
        allProductPresenter?.getAllProduct()
        return view
    }

    private fun listeners() {
        binding?.imgadd?.setOnClickListener(this)
        binding?.backActivity?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
          when (p0?.id) {
//             R.id.imgadd ->{  findNavController().navigate(R.id.action_allProductFragment_to_addProductFragment)}
//              R.id.backActivity ->{  findNavController().navigate(R.id.action_allProductFragment_to_allViewofShopFragment)}

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
//    override fun getdata(activity: FragmentActivity, data: Data) {
//        Glide.with(activity)
//            .load(data.avatar).placeholder(R.drawable.applogo)
//            .into(binding!!.storeimage)
//    }
}



