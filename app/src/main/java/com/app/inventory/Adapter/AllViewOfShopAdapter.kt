package com.app.inventory.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.app.inventory.Interface.GridviewClickListener
import com.app.inventory.R
import com.app.inventory.StaticModels.ViewOfAllshopsModel

class AllViewOfShopAdapter(
    private val activity: FragmentActivity,
    private val arraylist: ArrayList<ViewOfAllshopsModel>,
    private val clickListener: GridviewClickListener
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var tv_inventory: TextView
    private lateinit var productsimage: ImageView
    private lateinit var linlayout: LinearLayout
    override fun getCount(): Int {
        return arraylist.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View? {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.allshopviewitem, null)
        }
        tv_inventory = convertView!!.findViewById(R.id.tv_inventory)
        productsimage = convertView!!.findViewById(R.id.productsimage)
        linlayout = convertView!!.findViewById(R.id.linlayout)

        tv_inventory.text = arraylist.get(position).name
        productsimage.setImageResource(arraylist.get(position).imageicon!!)

        linlayout.setOnClickListener {
            clickListener.clickinglistner(position)
        }

        return convertView
    }
}