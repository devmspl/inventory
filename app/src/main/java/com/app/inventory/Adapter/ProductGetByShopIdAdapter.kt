package com.app.inventory.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.app.inventory.Fragments.ProductGetByShopIdFragment.ProductGetByShopIdFragment
import com.app.inventory.Models.ProductGetByShopid.Data
import com.app.inventory.R
import com.bumptech.glide.Glide

class ProductGetByShopIdAdapter(
    private val activity: FragmentActivity,
    private val getProductByShopIdFragment: ProductGetByShopIdFragment,
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
            convertView = layoutInflater!!.inflate(R.layout.getproductbyshopid_gridview, null)

        }

        val tv_productNameame: TextView =
            convertView?.findViewById(R.id.tv_productNameame) as TextView
        val productImg: ImageView = convertView?.findViewById(R.id.productImg) as ImageView

        val product = list[p0]
        list.get(p0).avatar?.let {
            Glide.with(activity)
                .load(it)
                .placeholder(R.drawable.biscuit)
                .into(productImg)
        }
        tv_productNameame.setText(list.get(p0).productname)


        productImg.setOnClickListener {
            val bundle = Bundle()
            val intent = Intent()
            bundle.putString("_id", product._id)
            bundle.putString("productName", product.productname)
            bundle.putString("avatar", product.avatar)
            bundle.putString("code", product.code)
            bundle.putString("description", product.description)
             bundle.putString("quantity", product.quantity)
            bundle.putInt("sellingprice", product.sellingprice)
            NavHostFragment.findNavController(getProductByShopIdFragment)
                .navigate(R.id.action_getProductByShopIdFragment_to_productDetailsFragment, bundle)
        }
        return convertView
    }
}