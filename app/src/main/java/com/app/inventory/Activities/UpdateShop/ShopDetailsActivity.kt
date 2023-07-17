package com.app.inventory.Activities.UpdateShop

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.inventory.Activities.UpdateShop.Presenter.DeleteShopPresente
import com.app.inventory.Activities.UpdateShop.View.ShopDeleteByShopIdView
import com.app.inventory.Fragments.ShopGetByUserIdFragment.ShopgetByUserIdFragment
import com.app.inventory.R
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.ActivityShopDetailsBinding
import com.bumptech.glide.Glide

class ShopDetailsActivity : AppCompatActivity(), ShopDeleteByShopIdView,
    View.OnClickListener {
    private var binding: ActivityShopDetailsBinding? = null
    private var deleteShopPresente: DeleteShopPresente? = null
    private val activity: Activity? = null
    private var id: String? = null
    private var avater: String? = null
    private var name: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopDetailsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        id = CSPreferences.readString(this, Utils.SID)
          name = CSPreferences.readString(this, Utils.SNAME)
         avater = CSPreferences.readString(this, Utils.SAVATAR)
        val  phone = CSPreferences.readString(this, Utils.SNO)
        val address = CSPreferences.readString(this, Utils.SADDRES)


        binding?.shopName?.text = name
        binding?.contact?.text = phone
        binding?.address?.text = address
        Glide.with(this)
            .load(avater)
            .into(binding!!.shopImageee)

        listeners()
    }

    private fun listeners() {
        binding?.backActivity?.setOnClickListener(this)
        binding?.update?.setOnClickListener(this)
        binding?.delete11?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.backActivity -> {
                val intent = Intent(this, ShopgetByUserIdFragment::class.java)
                CSPreferences.putString(this,Utils.SNAME, name!!)
                CSPreferences.putString(this,Utils.SAVATAR, avater!!)
                startActivity(intent)

            }

            R.id.delete11 -> {
                showAlert()
            }

            R.id.update -> {
                Utils.movetoActivity(this, UpdateShopActivity())
               CSPreferences.putString(this, Utils.SID, id!!)
                CSPreferences.clear(this, avater)
            }

        }
    }

    private fun showAlert() {
        val dialogView: View =
            LayoutInflater.from(this).inflate(R.layout.dialog_confirm_delete, null)
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)

        val alertDialog = dialogBuilder.create()

        val yesButton = dialogView.findViewById<Button>(R.id.yes)
        val noButton = dialogView.findViewById<Button>(R.id.no)

        yesButton.setOnClickListener {
            deleteShopPresente = DeleteShopPresente(this, this, this)
            deleteShopPresente!!.shopDeleteByShopId()
            alertDialog.dismiss()

        }

        noButton.setOnClickListener {

            alertDialog.dismiss()
            Toast.makeText(this, "Product No Delete", Toast.LENGTH_SHORT).show()
        }

        alertDialog.show()
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