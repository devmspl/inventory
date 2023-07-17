package com.app.inventory.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.inventory.Models.OrderDetailsModel
import com.app.inventory.R
import com.bumptech.glide.Glide

class OrderDetailsAdapter(
    private val listOrders: List<OrderDetailsModel>,
    private val context: Context
) :
    RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.img)
        val txv = itemView.findViewById<TextView>(R.id.tv)
        val txv2 = itemView.findViewById<TextView>(R.id.tv2)
        val txv3 = itemView.findViewById<TextView>(R.id.tv3)
        val txv4 = itemView.findViewById<TextView>(R.id.tv4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_details_recycleview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOrders.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = listOrders[position]
        Glide.with(context!!)
            .load(R.drawable.facewash)
            .into(holder.imageView)
        holder.txv.text = listOrders[position].textView
        holder.txv2.text = listOrders[position].textView2
        holder.txv3.text = listOrders[position].textView3
        holder.txv4.text = listOrders[position].textView4

    }
}