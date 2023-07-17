package com.app.inventory.Fragments.ChangePasswordFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.app.inventory.Fragments.ChangePasswordFragment.Presenter.ChangePassPresenter
import com.app.inventory.Fragments.ChangePasswordFragment.View.ChangePassView
import com.app.inventory.R
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.FragmentChangePasswordBinding


class ChangePasswordFragment : Fragment(),ChangePassView, View.OnClickListener {
    private var binding:FragmentChangePasswordBinding?=null
    private var changePassPresenter:ChangePassPresenter?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        val view: View = binding!!.root

        listeners()

        changePassPresenter = ChangePassPresenter(activity as FragmentActivity,this,this)
        return view
    }

    fun listeners(){
        binding?.btnsubmit?.setOnClickListener(this)
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity,msg)

    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity,activity?.fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){

            R.id.btnsubmit->{
                changePassPresenter?.changePasswordMethod(binding!!.etpassword.text.toString())

            }
        }
    }

}