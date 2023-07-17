package com.app.inventory.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.inventory.R
import com.app.inventory.StaticModels.SearchProductItem
import com.bumptech.glide.Glide

class SearchProductItemAdapter(
    private val searchProductItem: List<SearchProductItem>,
    private val context: Context,
) : RecyclerView.Adapter<SearchProductItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_list_products, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchProductItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = searchProductItem[position]
        holder.textView.text = searchProductItem[position].textView
        holder.textView2.text = searchProductItem[position].textView2
        Glide.with(context).load(R.drawable.searchproduct).into(holder.imageView)

        // holder.textView3.text = searchProductItem.toString()
        // holder.textView4.text = searchProductItem.toString()
       // Glide.with(context).load(R.drawable.searchproduct).into(holder.imageView2)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView= itemView.findViewById<TextView>(R.id.tvv)
        val textView2 = itemView.findViewById<TextView>(R.id.tvv2)
        val imageView = itemView.findViewById<ImageView>(R.id.searchproduct)

//        val textView3 = itemView.findViewById<TextView>(R.id.tvv3)
//        val textView4 = itemView.findViewById<TextView>(R.id.tvv4)
//        val imageView2 = itemView.findViewById<ImageView>(R.id.searchproduct2)
    }
}

