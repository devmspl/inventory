package com.app.inventory.Adapter

import android.app.Activity
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
import com.app.inventory.Activities.SearchProductActivity.SearchProductActivity
import com.app.inventory.Fragments.SearchFragment.SearchProductFragment
import com.app.inventory.Models.SearchproductModel.Data
import com.app.inventory.R
import com.bumptech.glide.Glide

class SearchProductAdapter (
    private val activity: FragmentActivity,
    private val searchProductFragment: SearchProductFragment,
    private val list: List<Data>,
) : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list   }

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
            convertView = layoutInflater!!.inflate(R.layout.searchproductitems, null)

        }

        val tv_productNameame: TextView = convertView?.findViewById(R.id.tv_productNam) as TextView
        val productImg: ImageView = convertView?.findViewById(R.id.productIm) as ImageView
        val sp = list[p0]
        list.get(p0).avatar?.let {
            Glide.with(activity)
                .load(it)
                .placeholder(R.drawable.biscuit)
                .into(productImg)
        }
        tv_productNameame.setText(list.get(p0).productname)
        productImg.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("_id", sp._id)
            bundle.putString("productName",sp.productname)
            bundle.putString("avatar", sp.avatar)
            bundle.putString("code", sp.code)
            bundle.putString("description", sp.description)
            bundle.putInt("quantity", sp.quantity.toInt())
            // bundle.putString("quantity", product.quantity)
            bundle.putInt("sellingprice", sp.sellingprice)
            NavHostFragment.findNavController(searchProductFragment)
                .navigate(R.id.action_searchproductFragment_to_productDetailsFragment, bundle)
        }
        return convertView
    }
}