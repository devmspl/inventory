package com.app.inventory.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.app.inventory.Fragments.SearchShopFragment.SearchShopFragment
import com.app.inventory.Models.SearchShopModel.Data
import com.app.inventory.R
import com.bumptech.glide.Glide

class SearchShopAdapter(
    private val activity: FragmentActivity,
    private val searchShopFragment: SearchShopFragment,
    private val list: List<Data>,
) : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list
    }

//    override fun getItem(p0: Int): Any? {
//        return null
//    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.search_shop_list, null)

        }

        val tv_productNameame: TextView = convertView?.findViewById(R.id.tv_productNam) as TextView
        val productImg: ImageView = convertView?.findViewById(R.id.productIm) as ImageView
        list.get(p0).avatar?.let {
            Glide.with(activity)
                .load(it)
                .placeholder(R.drawable.facewash)
                .into(productImg)
        }
        tv_productNameame.setText(list.get(p0).name)
        productImg.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("shopid", list.get(p0)._id)
            NavHostFragment.findNavController(searchShopFragment)
                .navigate(R.id.action_searchShopFragment_to_getProductByShopIdFragment, bundle)
        }
        return convertView
    }
}