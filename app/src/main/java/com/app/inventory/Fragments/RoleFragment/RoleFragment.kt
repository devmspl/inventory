package com.app.inventory.Fragments.RoleFragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.app.inventory.Fragments.RoleFragment.Presenter.RolePresenter
import com.app.inventory.Fragments.RoleFragment.View.RoleView
import com.app.inventory.R
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.FragmentRoleBinding

class RoleFragment : Fragment(), RoleView, View.OnClickListener {
    private var binding: FragmentRoleBinding? = null
    private var rolePresenter: RolePresenter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRoleBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        listiner()
        initi()
        val id = CSPreferences.readString(activity!!, Utils.USERID)
        return view
    }

    private fun initi() {
        binding?.addShop?.findViewById<Button>(R.id.addShop)
        binding?.custmor?.findViewById<Button>(R.id.custmor)
    }

    private fun listiner() {
        binding?.addShop?.setOnClickListener(this)
        binding?.custmor?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.addShop -> {
                    rolePresenter = RolePresenter(activity as FragmentActivity, this, this)
                    rolePresenter?.shopGetByUser()
                }
                   // findNavController().navigate(R.id.action_roleFragment_to_homeActivity)
            R.id.custmor -> {
                findNavController().navigate(R.id.action_roleFragment_to_homeMainActivity)
            }


        }
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity, msg)
    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity, activity?.fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }
}

