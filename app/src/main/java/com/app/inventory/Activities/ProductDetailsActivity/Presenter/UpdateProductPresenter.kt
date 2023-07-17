package com.app.inventory.Activities.ProductDetailsActivity.Presenter

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.widget.Toast
import com.app.inventory.Activities.ProductDetailsActivity.ProductDetailsActivity
import com.app.inventory.Activities.ProductDetailsActivity.UpdateProductActivity
import com.app.inventory.Activities.ProductDetailsActivity.View.ProductUpdateByIdView
import com.app.inventory.Api.WebServices
import com.app.inventory.Fragments.ProductGetByidFragment.ProductGetByIdFragment
import com.app.inventory.Handler.ProductUpdateByidHandler
import com.app.inventory.Handler.UpdateProductImageHandler
import com.app.inventory.Models.ProductUpdateByiD.ProductUpdateByIdExample
import com.app.inventory.Models.ProductUpdateImageModel.ProductImageUpdateExample
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.util.Random

class UpdateProductPresenter(
    private val activity: Activity,
    private val productUpdateByIdView: ProductUpdateByIdView,
    private val updateProductActivity: UpdateProductActivity,
    private val bitmap: Bitmap,

    ) {
    private var intent:Intent?=null
    private var imagePart: MultipartBody.Part? = null
    private var id: String? = null
    fun productUpdateById(
        productname: String,
        description: String,
        quantity: String,
        date: String,
        costprice: Int,
        sellingprice: Int,
    ) {

        if (Utils.isNetworkConnected(activity!!)) {
            val token = CSPreferences.readString(activity, Utils.TOKEN)
            id = updateProductActivity.id

            productUpdateByIdView.showDialog(activity)
            WebServices.Factory1.getInstance()?.productUpdateByIdMethod(
                token!!,
                id!!,
                productname,
                description,
                quantity,
                date,
                costprice,
                sellingprice,
                object : ProductUpdateByidHandler {
                    override fun onSuccess(productUpdateByIdExample: ProductUpdateByIdExample?) {
                        productUpdateByIdView.hideDialog()
                        if (productUpdateByIdExample != null) {
                            if (productUpdateByIdExample.isSuccess == true) {
                                if (productUpdateByIdExample.data.id.isNotEmpty()) {
                                    val id = productUpdateByIdExample.data.id
                                    val intent=intent?.extras
                                   intent?.putString("pproductname",productUpdateByIdExample.data.productname)
                                   intent?.putString("ddescription",productUpdateByIdExample.data.description)
                                   intent?.putString("qquantity",productUpdateByIdExample.data.quantity)
                                   intent?.putString("ddate",productUpdateByIdExample.data.date)
                                   intent?.putString("iid",productUpdateByIdExample.data.id)
                                   intent?.putInt("ccostprice",productUpdateByIdExample.data.costprice)
                                   intent?.putInt("ssellingprice",productUpdateByIdExample.data.sellingprice)
                                    uploadProductImage(bitmap)
                                    productUpdateByIdView?.showMessage(
                                        activity,
                                        productUpdateByIdExample.message
                                    )

                                } else {
                                    productUpdateByIdView?.showMessage(
                                        activity,
                                        productUpdateByIdExample.message
                                    )
                                }
                            } else {
                                productUpdateByIdView?.showMessage(
                                    activity,
                                    productUpdateByIdExample.message
                                )
                            }
                        } else {
                            productUpdateByIdView?.showMessage(
                                activity,
                                productUpdateByIdExample?.message
                            )
                        }
                    }

                    override fun onError(message: String?) {
                        productUpdateByIdView.hideDialog()
                        productUpdateByIdView.showMessage(activity, message)
                    }
                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadProductImage(bitmap: Bitmap) {
        val id = id

        if (bitmap != null) {
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 40, stream)
            val photoByteArray = stream.toByteArray()
            val requestFile =
                RequestBody.create("multipart/form-data".toMediaTypeOrNull(), photoByteArray)

            val random = Random()
            imagePart = MultipartBody.Part.createFormData(
                "file",
                "abc" + random.nextInt(1000000) + ".jpg",
                requestFile
            )
        }
        if (Utils.isNetworkConnected(activity!!)) {
            productUpdateByIdView?.showDialog(activity)
            WebServices.Factory1.getInstance()
                ?.updateProductImageMethod(id!!, imagePart!!, object :
                    UpdateProductImageHandler {
                    override fun onSuccess(productImageUpdateExample: ProductImageUpdateExample?) {
                        productUpdateByIdView?.hideDialog()
                        if (productImageUpdateExample != null) {
                            if (productImageUpdateExample.isSuccess === true) {
                               var intent=Intent(activity,ProductDetailsActivity::class.java)
                                activity.startActivity(intent)
                                productUpdateByIdView?.showMessage(
                                    activity,
                                    productImageUpdateExample.data
                                )

                            } else {
                                productUpdateByIdView?.showMessage(
                                    activity,
                                    productImageUpdateExample.data
                                )
                            }
                        } else {
                            productUpdateByIdView?.showMessage(
                                activity,
                                productImageUpdateExample?.data
                            )
                        }
                    }


                    override fun onError(message: String?) {
                        productUpdateByIdView?.hideDialog()
                        productUpdateByIdView?.showMessage(activity, message)
                    }
                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }


}