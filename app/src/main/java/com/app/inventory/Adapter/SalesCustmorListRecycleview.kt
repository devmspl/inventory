package com.app.inventory.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.inventory.R
import com.app.inventory.StaticModels.SalesCustmorModel

class SalesCustmorListRecycleview(private val arrayList: ArrayList<SalesCustmorModel>) :
    RecyclerView.Adapter<SalesCustmorListRecycleview.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sales_customer_recycleview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }
}