package com.app.inventory.Activities.ProductDetailsActivity

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.inventory.Activities.ProductDetailsActivity.Presenter.DeleteProductPresenter
import com.app.inventory.Activities.ProductDetailsActivity.View.DeleteProductView
import com.app.inventory.Fragments.ProductGetByShopIdFragment.ProductGetByShopIdFragment
import com.app.inventory.R
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.ActivityProductDetailsBinding
import com.bumptech.glide.Glide

class ProductDetailsActivity : AppCompatActivity(), View.OnClickListener,
    DeleteProductView {
    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var deleteProductPresenter: DeleteProductPresenter
    var id: String? = null
    var avatar: String? = null
    var code: String? = null
    var description: String? = null
    var date: String? = null
    var costPrice: String? = null
    var quanlity: String? = null
    var sellingPrice: String? = null
    var productname: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //update befor get data here
          getProductDetailsfromUpdateActivity()
        //data from product by id
        getproductDetailsFromProductbyId()
        //initilize
        initi()

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.backActivity -> {
                val intent = Intent(this, ProductGetByShopIdFragment::class.java)
                startActivity(intent)
            }

            R.id.delete -> {
                showAlert()
            }

            R.id.updateProduct -> {
                val intent = Intent(this, UpdateProductActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("avatar", avatar)
                intent.putExtra("code", code)
                intent.putExtra("description", description)
                intent.putExtra("date", date)
                intent.putExtra("quanlity", quanlity)
                intent.putExtra("productname", productname)
                intent.putExtra("sellingprice", sellingPrice)
                intent.putExtra("costprice", costPrice)
                startActivity(intent)
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
            deleteProductPresenter = DeleteProductPresenter(this, this, this)
            deleteProductPresenter.deleteProduct()
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

    private fun initi() {
        binding.backActivity.setOnClickListener(this)
        binding.delete.setOnClickListener(this)
        binding.productImageee.setOnClickListener(this)
        binding.updateProduct.setOnClickListener(this)
    }

    private fun getproductDetailsFromProductbyId() {
       val  bundle = intent.extras
         id = bundle?.getString("id")
         avatar = bundle?.getString("avatar")
         code = bundle?.getString("code")
         description = bundle?.getString("description")
         date = bundle?.getString("date")
         quanlity = bundle?.getString("quanlity")
         productname = bundle?.getString("productname")
         sellingPrice = bundle?.getString("sellingprice")
         costPrice = bundle?.getString("costprice")

        Glide.with(this)
            .load(avatar)
            .into(binding.productImageee)
        binding.productName.text = productname
        binding.description.text = description
        binding.code.text = code
        binding.quantity1.text = quanlity
        binding.date1.text = date
        binding.costPrize.text = costPrice
        binding.sellingPrize.text = sellingPrice
    }

    private fun getProductDetailsfromUpdateActivity() {
        val bundle = intent.extras
        if (bundle != null) {
            id = bundle.getString("iid")
            avatar = bundle.getString("aavatar")
            code = bundle.getString("ccode")
            description = bundle.getString("ddescription")
            date = bundle.getString("ddate")
            quanlity = bundle.getString("qquanlity")
            productname = bundle.getString("pproductname")
            sellingPrice = bundle.getString("ssellingprice")
            costPrice = bundle.getString("ccostprice")
    }

}}
