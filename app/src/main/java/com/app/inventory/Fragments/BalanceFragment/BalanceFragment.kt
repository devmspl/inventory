package com.app.inventory.Fragments.BalanceFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.inventory.R
import com.app.inventory.databinding.FragmentBalanceBinding
import com.app.inventory.databinding.FragmentInventoryBinding


class BalanceFragment : Fragment(), View.OnClickListener {
    private var binding:FragmentBalanceBinding?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBalanceBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        binding!!.backActivity.setOnClickListener(this)
        return view
    }

    override fun onClick(p0: View?) {
when(p0?.id){
    R.id.backActivity->{
        findNavController().navigate(R.id.action_balanceFragment_to_allViewofShopFragment)
    }
}    }


}