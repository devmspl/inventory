package com.app.inventory.Fragments.LoginFragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.app.inventory.Fragments.LoginFragment.Presenter.LoginPresenter
import com.app.inventory.Fragments.LoginFragment.View.LoginView
import com.app.inventory.R
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.FragmentLoginBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class LoginFragment : Fragment(), View.OnClickListener,LoginView {
    private var binding: FragmentLoginBinding? = null
    var bottomSheetDialog: BottomSheetDialog? = null
    private var loginPresenter:LoginPresenter?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view: View = binding!!.root

        listeners()
        loginPresenter = LoginPresenter(activity as FragmentActivity,this,this)
        return view
    }

    private fun listeners() {
        binding?.btnlogin?.setOnClickListener(this)
        binding?.tvforgotpassword?.setOnClickListener(this)
        binding?.tvSignup?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (p0 == binding?.btnlogin) {
            loginPresenter?.loginMethod(binding?.etemail?.text.toString(),binding?.etpassword?.text.toString())
        }else if(p0==binding?.tvforgotpassword){
            showBottomSheetDialog()
        }else if(p0==binding?.tvSignup){
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun showBottomSheetDialog() {
        bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
        bottomSheetDialog?.setContentView(R.layout.forgotpassbottomsheet)
        var tvforgotpassword = bottomSheetDialog?.findViewById<TextView>(R.id.tvforgotpassword)
        var btnsendotp = bottomSheetDialog?.findViewById<Button>(R.id.btnsendotp)
        var etforgotemail = bottomSheetDialog?.findViewById<EditText>(R.id.etforgotemail)

        bottomSheetDialog?.show()

        tvforgotpassword!!.setOnClickListener(this)
        btnsendotp!!.setOnClickListener {
            bottomSheetDialog?.dismiss()
            loginPresenter?.forgotpassMethod(etforgotemail?.text.toString())

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