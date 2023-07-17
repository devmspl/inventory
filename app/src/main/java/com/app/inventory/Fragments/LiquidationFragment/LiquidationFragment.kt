package com.app.inventory.Fragments.LiquidationFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.FragmentActivity
import com.app.inventory.Adapter.CustomAdapter
import com.app.inventory.Adapter.InventoryGridAdapter
import com.app.inventory.R
import com.app.inventory.StaticModels.InventoryModels
import com.app.inventory.StaticModels.SpinnerInventoryYearModel
import com.app.inventory.databinding.FragmentBalanceBinding
import com.app.inventory.databinding.FragmentInventoryBinding
import com.app.inventory.databinding.FragmentLiquidationBinding


class LiquidationFragment : Fragment(),AdapterView.OnItemSelectedListener  {
    private var binding:FragmentLiquidationBinding?=null
    private var inventoryGridAdapter: InventoryGridAdapter? = null
    private var arrayList: ArrayList<InventoryModels> = ArrayList()
    var yeararray = arrayOf("2012","2013","2014","2015","2016","2017","2019")
    private var arraylistdata: ArrayList<SpinnerInventoryYearModel> = ArrayList<SpinnerInventoryYearModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLiquidationBinding.inflate(inflater, container, false)
        val view: View = binding!!.root


        arrayList.add(InventoryModels(getString(R.string.saleinlek), "0"))
        arrayList.add(InventoryModels(getString(R.string.saleineuro), "0"))
        arrayList.add(InventoryModels(getString(R.string.totinventory), "0"))
        arrayList.add(InventoryModels(getString(R.string.totinventory), "0"))
        arrayList.add(InventoryModels(getString(R.string.soldinventory), "0"))
        arrayList.add(InventoryModels(getString(R.string.totinventorycost), "0"))

        inventoryGridAdapter = InventoryGridAdapter(activity as FragmentActivity, arrayList)
        binding?.inventorygridview?.adapter = inventoryGridAdapter



        arraylistdata.add(SpinnerInventoryYearModel("2012"))
        arraylistdata.add(SpinnerInventoryYearModel("2013"))
        arraylistdata.add(SpinnerInventoryYearModel("2014"))
        arraylistdata.add(SpinnerInventoryYearModel("2015"))
        binding?.spinner!!.setOnItemSelectedListener(this)

        val adapter = CustomAdapter(
            activity as FragmentActivity,
            R.layout.simple_spinner_item, arraylistdata
        )
        binding?.spinner!!.setAdapter(adapter)
        binding?.spinnermonth!!.setAdapter(adapter)

        return view
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }


}