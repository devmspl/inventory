package com.app.inventory.Fragments.ViewCustomerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.inventory.Adapter.ViewCustomerRecyclerAdapter
import com.app.inventory.R
import com.app.inventory.databinding.FragmentViewCustomerBinding


class ViewCustomerFragment : Fragment(), View.OnClickListener {
    private var binding: FragmentViewCustomerBinding? = null
    private var viewCustomerRecyclerAdapter: ViewCustomerRecyclerAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewCustomerBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        listners()
        viewCustomerRecyclerAdapter = ViewCustomerRecyclerAdapter(activity as FragmentActivity)
        binding!!.customerRecyleview.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding!!.customerRecyleview.adapter = viewCustomerRecyclerAdapter


        return view
    }

    private fun listners() {
        binding!!.imgadd.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.imgadd -> findNavController().navigate(R.id.action_viewCustomerFragment_to_addCustomerFragment)
        }
    }


}