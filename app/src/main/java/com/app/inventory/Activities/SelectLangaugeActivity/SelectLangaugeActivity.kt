package com.app.inventory.Activities.SelectLangaugeActivity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.inventory.Activities.Activiti.LoginActivity
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.ActivitySelectLangaugeBinding

class SelectLangaugeActivity : AppCompatActivity(), View.OnClickListener {
    private var binding:ActivitySelectLangaugeBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectLangaugeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        listeners()

        var status = CSPreferences.readString(this,Utils.USERLOGIN)



    }
    private  fun listeners(){
        binding?.btnGetstarted?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (p0==binding?.btnGetstarted){
            Utils.movetoActivity(this, LoginActivity())
        }
    }



}