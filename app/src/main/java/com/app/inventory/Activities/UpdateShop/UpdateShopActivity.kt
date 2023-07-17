package com.app.inventory.Activities.UpdateShop

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.inventory.Activities.UpdateShop.Presenter.UpdateShopPresenter
import com.app.inventory.Activities.UpdateShop.View.UpdateShopView
import com.app.inventory.R
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.ActivityUpdateShop2Binding
import com.bumptech.glide.Glide
import java.io.IOException

class UpdateShopActivity : AppCompatActivity(), UpdateShopView, View.OnClickListener {
    private lateinit var updateShopPresenter: UpdateShopPresenter
    private var binding: ActivityUpdateShop2Binding? = null
    private val pickImage = 100
    private var imageUri: Uri? = null
    var bitmap: Bitmap? = null
    private var avater: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateShop2Binding.inflate(layoutInflater)
        setContentView(binding!!.root)

        listeners()
        val id = CSPreferences.readString(this, Utils.SID)
        val name = CSPreferences.readString(this, Utils.SNAME)
        avater = CSPreferences.readString(this, Utils.SAVATAR)
        val phone = CSPreferences.readString(this, Utils.SNO)
        val address = CSPreferences.readString(this, Utils.SADDRES)
        Glide.with(this)
            .load(avater)
            .into(binding!!.shopImage22)
        binding?.name22?.setText(name)
        binding?.contact22?.setText(phone)
        binding?.address22?.setText(address)


    }

    private fun listeners() {
        binding?.backActivity?.setOnClickListener(this)
        binding?.update22?.setOnClickListener(this)
        binding?.shopImage22?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.backActivity -> {
                Utils.movetoActivity(this, ShopDetailsActivity())

            }

            R.id.update22 -> {
                val contactNumString = binding!!.contact22?.text.toString()
                val contactNum: Int? = contactNumString.toIntOrNull()
                if (contactNum != null) {
                    updateShopPresenter?.updateShop(
                        binding!!.name22?.text.toString(),
                        binding!!.address22?.text.toString(),
                        contactNum
                    )
                }
            }

            R.id.shopImage22 -> {
                val gallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, pickImage)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this!!.contentResolver, imageUri)
                updateShopPresenter = UpdateShopPresenter(
                    this, this, this, bitmap!!
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }

            binding?.shopImage22!!.setImageURI(imageUri)
            CSPreferences.putString(this, Utils.SAVATAR, imageUri.toString())


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