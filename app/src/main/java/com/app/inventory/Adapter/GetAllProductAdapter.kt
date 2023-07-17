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
import com.app.inventory.Fragments.AllProductFragment.AllProductFragment
import com.app.inventory.Models.GetAllProductModel.Item
import com.app.inventory.R
import com.bumptech.glide.Glide

class GetAllProductAdapter(
    private val activity: FragmentActivity,
    private val allProductFragment: AllProductFragment,
    private val list: List<Item>,
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
            convertView = layoutInflater!!.inflate(R.layout.listofproduct_gridview, null)

        }

        val tv_productNameame: TextView = convertView?.findViewById(R.id.tv_productNameame) as TextView
        val productImg: ImageView = convertView?.findViewById(R.id.productImg) as ImageView
        list.get(p0).avatar?.let {
            Glide.with(activity)
                .load(it)
                .placeholder(R.drawable.biscuit)
                .into(productImg)
        }
        tv_productNameame.setText(list.get(p0).productname)
        productImg.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id", list.get(p0).id)
//            NavHostFragment.findNavController(allProductFragment)
//                .navigate(R.id.action_allProductFragment_to_productDetailsActivity, bundle)
        }
        return convertView
    }
}