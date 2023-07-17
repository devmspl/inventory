package com.app.inventory.Activities.ProductDetailsActivity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.inventory.Activities.ProductDetailsActivity.Presenter.UpdateProductPresenter
import com.app.inventory.Activities.ProductDetailsActivity.View.ProductUpdateByIdView
import com.app.inventory.Activities.UpdateShop.Presenter.UpdateShopPresenter
import com.app.inventory.R
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.ActivityUpdateProductBinding
import com.bumptech.glide.Glide
import java.io.IOException

class UpdateProductActivity : AppCompatActivity(), ProductUpdateByIdView,View.OnClickListener {
    private var binding: ActivityUpdateProductBinding? = null
    private lateinit var updateProductPresenter: UpdateProductPresenter
    private lateinit var productDetailsActivity: ProductDetailsActivity
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var bitmap: Bitmap? = null
    var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        listiner()
        //details  product
        getDetails()

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.updateProduct11 -> {
                val costPrizeString = binding!!.costPrize11?.text.toString()
                val sellingPrizeString = binding!!.sellingPrize11?.text.toString()
                val costP: Int = costPrizeString.toIntOrNull() ?: 0
                val sellingp: Int = sellingPrizeString.toIntOrNull() ?: 0

                updateProductPresenter?.productUpdateById(
                    binding!!.pName?.text.toString(),
                    binding!!.description11?.text.toString(),
                    binding!!.quantity11?.text.toString(),
                    binding!!.date11?.text.toString(),
                    costP,
                    sellingp
                )

            }

            R.id.productImage11 -> {
                val gallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, pickImage)
            }
        }
    } override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this!!.contentResolver, imageUri)
                updateProductPresenter = UpdateProductPresenter(
                    this, this, this, bitmap!!
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }

            binding?.productImage11!!.setImageURI(imageUri)
          // intent.putExtra("avatar",imageUri.toString())

        }}

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity, msg)
    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity, activity?.fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }
    private fun getDetails() {
        id = intent.getStringExtra("id")
        val avatar = intent.getStringExtra("avatar")
        val description = intent.getStringExtra("description")
        val date = intent.getStringExtra("date")
        val quanlity = intent.getStringExtra("quanlity")
        val productname = intent.getStringExtra("productname")
        val sellingPrice = intent.getStringExtra("sellingprice")
        val costPrice = intent.getStringExtra("costprice")

        Glide.with(this)
            .load(avatar)
            .into(binding!!.productImage11)
        binding!!.pName.setText(productname)
        binding!!.description11.setText(description)
        binding!!.quantity11.setText(quanlity)
        binding!!.date11.setText(date)
        binding!!.costPrize11.setText(costPrice)
        binding!!.sellingPrize11.setText(sellingPrice)
    }

    private fun listiner() {
        binding?.updateProduct11?.setOnClickListener(this)
        binding?.productImage11?.setOnClickListener(this)
    }
}