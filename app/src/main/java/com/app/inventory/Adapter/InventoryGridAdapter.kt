package com.app.inventory.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.app.inventory.R
import com.app.inventory.StaticModels.InventoryModels

class InventoryGridAdapter(
    private val activity: FragmentActivity,
    private val arrayList: ArrayList<InventoryModels>
) :BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private lateinit var tv_sale: TextView
    private lateinit var tv_saleamount: TextView


    override fun getCount(): Int {
        return arrayList.size
    }


    override fun getItem(position: Int): Any? {
        return null
    }


    override fun getItemId(position: Int): Long {
        return 0
    }

    // in below function we are getting individual item of grid view.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.inventorygridviewitem, null)
        }

        tv_sale = convertView!!.findViewById(R.id.tv_sale)
        tv_saleamount = convertView!!.findViewById(R.id.tv_saleamount)
        tv_sale.text = arrayList.get(position).salename
        tv_saleamount.text = arrayList.get(position).saleamount
//        courseIV.setImageResource(courseList.get(position).courseImg)
//        courseTV.setText(courseList.get(position).courseName)
        return convertView
    }
}