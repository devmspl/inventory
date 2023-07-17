package com.app.inventory.Fragments.RegisterFragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.app.inventory.Fragments.RegisterFragment.Presenter.RegisterPresenter
import com.app.inventory.Fragments.RegisterFragment.View.RegisterView
import com.app.inventory.R
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment(), View.OnClickListener,RegisterView {
    private var binding : FragmentRegisterBinding?=null
    private var registerPresenter:RegisterPresenter?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view: View = binding!!.root

        listeners()
        registerPresenter = RegisterPresenter(activity as FragmentActivity,this,this)
        return view
    }

    private fun listeners() {
        binding?.tvLogin!!.setOnClickListener(this)
        binding?.btnregister!!.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tvLogin-> findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            R.id.btnregister->{
                registerPresenter?.signupMethod(binding?.etname?.text.toString(),
                    binding?.etemail?.text.toString(),
                    binding?.etphonenumber?.text.toString(),
                    binding?.etpassword?.text.toString())

            }

        }
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity, msg)

    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity, requireActivity()!!.fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }
}