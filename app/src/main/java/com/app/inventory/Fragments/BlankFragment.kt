package com.app.inventory.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.inventory.R
import com.app.inventory.databinding.FragmentBlankBinding
import com.app.inventory.databinding.FragmentLoginBinding

class BlankFragment : Fragment() {
    private var binding:FragmentBlankBinding?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlankBinding.inflate(inflater, container, false)
        val view: View = binding!!.root

//        binding?.blanfgr?.setOnClickListener{
//            findNavController().navigate(R.id.action_blankFragment_to_homeActivity)
//        }
        return view
    }


}