package com.app.inventory.Fragments.ProductsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.inventory.Adapter.OrdersRecyclerviewAdapter
import com.app.inventory.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {
    private var binding:FragmentProductsBinding?=null
    private var ordersRecyclerviewAdapter:OrdersRecyclerviewAdapter?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProductsBinding.inflate(inflater, container, false)
        val view: View = binding!!.root

        ordersRecyclerviewAdapter = OrdersRecyclerviewAdapter(activity)
        binding!!.ordersrecyclerview.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        binding!!.ordersrecyclerview.adapter = ordersRecyclerviewAdapter


        return view
    }



}