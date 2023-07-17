package com.app.inventory.Fragments.AddCustomerFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.app.inventory.Adapter.AllViewOfShopAdapter
import com.app.inventory.R
import com.app.inventory.StaticModels.ViewOfAllshopsModel
import com.app.inventory.databinding.FragmentAddCustomerBinding


class AddCustomerFragment : Fragment(), View.OnClickListener {
    private var binding: FragmentAddCustomerBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddCustomerBinding.inflate(inflater, container, false)
        val view: View = binding!!.root

        listeners()


        return view
    }


    private fun listeners() {


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

        }

    }
}