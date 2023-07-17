package com.app.inventory.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.inventory.R
import com.app.inventory.StaticModels.ReviewProductModel
import com.bumptech.glide.Glide

class ReviewProductAdapter(
    private val arrayList: ArrayList<ReviewProductModel>,
    private val context: Context?,
) :
    RecyclerView.Adapter<ReviewProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_reviews, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = arrayList[position].textView
        Glide.with(context!!)
            .load(R.drawable.star)
            .into(holder.imageView)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.reviewText)
        val imageView = itemView.findViewById<ImageView>(R.id.reviewImage)
    }
}