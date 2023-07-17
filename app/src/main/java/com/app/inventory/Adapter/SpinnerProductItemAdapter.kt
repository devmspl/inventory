package com.app.inventory.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.app.inventory.R
import com.app.inventory.StaticModels.SpinnerProductQuantityModel

class SpinnerProductItemAdapter(context: Context, @LayoutRes private val resource: Int, objects: List<SpinnerProductQuantityModel>) :
    ArrayAdapter<SpinnerProductQuantityModel>(context, resource, objects) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val items: List<SpinnerProductQuantityModel> = objects

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater.inflate(resource, parent, false)

        val tvSelectedItem: TextView = view.findViewById(R.id.tv_productitemv)
        tvSelectedItem.text = items[position].quantity

        tvSelectedItem.setOnClickListener {
            // Handle click event
        }

        return view
    }
}