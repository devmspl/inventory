package com.app.inventory.Fragments.ShopGetByUserIdFragment

import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.app.inventory.Adapter.AllViewOfShopAdapter
import com.app.inventory.Fragments.ShopGetByUserIdFragment.Presenter.ShopGetByUserIdPresenter
import com.app.inventory.Fragments.ShopGetByUserIdFragment.View.ShopGetByUserIdView
import com.app.inventory.Interface.GridviewClickListener
import com.app.inventory.R
import com.app.inventory.StaticModels.ViewOfAllshopsModel
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.FragmentShopGetbyUeridBinding
import com.bumptech.glide.Glide

class ShopgetByUserIdFragment : Fragment(), GridviewClickListener, ShopGetByUserIdView,
    View.OnClickListener {
    private var binding: FragmentShopGetbyUeridBinding? = null
    private var allViewOfShopAdapter: AllViewOfShopAdapter? = null
    private var arraylist: ArrayList<ViewOfAllshopsModel> = ArrayList()
    private var allViewofShopPresenter: ShopGetByUserIdPresenter? = null
    private val bitmap: Bitmap? = null
    private var name: String? = null
    private var avater: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentShopGetbyUeridBinding.inflate(inflater, container, false)
        val view: View = binding!!.root


         name = CSPreferences.readString(activity!!, Utils.SNAME)
         avater = CSPreferences.readString(activity!!, Utils.SAVATAR)

        binding?.shoptv?.setText(name)
        Glide.with(this)
            .load(avater)
            .into(binding!!.shopIg)

            listiner()
        allViewofShopPresenter = ShopGetByUserIdPresenter(activity as FragmentActivity, this, this)
        arraylist.clear()
        arraylist.add(
            ViewOfAllshopsModel(
                resources.getString(R.string.app_name),
                R.drawable.ic_home_work
            )
        )
        arraylist.add(ViewOfAllshopsModel("Product", R.drawable.add_product))
        arraylist.add(
            ViewOfAllshopsModel(
                resources.getString(R.string.customer),
                R.drawable.customer
            )
        )
        arraylist.add(
            ViewOfAllshopsModel(
                resources.getString(R.string.liquidetion),
                R.drawable.liquidation
            )
        )
        arraylist.add(ViewOfAllshopsModel(resources.getString(R.string.orders), R.drawable.ic_cart))
        arraylist.add(
            ViewOfAllshopsModel(
                resources.getString(R.string.balance),
                R.drawable.balancecl
            )
        )


        allViewOfShopAdapter = AllViewOfShopAdapter(activity as FragmentActivity, arraylist, this)
        binding?.allviewofshop?.adapter = allViewOfShopAdapter

        return view
    }

    private fun listiner() {
        binding!!.addPRoduct.setOnClickListener(this)
        binding!!.shopIg.setOnClickListener(this)
    }

    override fun clickinglistner(position: Int) {
        allViewofShopPresenter?.handleGridItemClick(position)
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showDialog(activity: Activity?) {
        // Show dialog here
    }

    override fun hideDialog() {
        // Hide dialog here
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.addPRoduct -> {
                findNavController().navigate(R.id.action_allViewofShopFragment_to_addProductFragment)
            }

            R.id.shopIg -> {
                findNavController().navigate(R.id.action_allViewofShopFragment_to_updateShopActivity)
                CSPreferences.clear(activity!!,name)
                CSPreferences.clear(activity!!,avater)
            }

        }
    }
}
