package com.app.inventory.Adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.app.inventory.Fragments.HomeFragment.HomeFragment
import com.app.inventory.Models.GetAllShopModel.Item
import com.app.inventory.R
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import com.bumptech.glide.Glide

class GetAllShopAdapter (
    private val activity: FragmentActivity,
    private val homeFragment: HomeFragment,
    private val list: List<Item>
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView

        if (layoutInflater == null) {
            layoutInflater =
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (convertView == null) {

            convertView = layoutInflater!!.inflate(R.layout.listofshop_gridview, null)
        }

        val tv_shopname: TextView = convertView?.findViewById(R.id.tv_shopname) as TextView
        val shopimg: ImageView = convertView?.findViewById(R.id.shopimg) as ImageView

        list.get(position).avatar?.let {
            Glide.with(activity)
                .load(it)
                .placeholder(R.drawable.applogo)
                .into(shopimg)
        }

        tv_shopname.setText(list.get(position).name)
        shopimg.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("shopid", list.get(position).id)
            NavHostFragment.findNavController(homeFragment)
                .navigate(R.id.action_homeFragment2_to_getProductByShopIdFragment, bundle)
        }
        return convertView
    }

}
