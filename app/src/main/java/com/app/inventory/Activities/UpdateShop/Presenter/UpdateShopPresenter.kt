package com.app.inventory.Activities.UpdateShop.Presenter

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.widget.Toast
import com.app.inventory.Activities.UpdateShop.ShopDetailsActivity
import com.app.inventory.Activities.UpdateShop.UpdateShopActivity
import com.app.inventory.Activities.UpdateShop.View.UpdateShopView
import com.app.inventory.Api.WebServices
import com.app.inventory.Handler.UpdateShopHandler
import com.app.inventory.Handler.UpdateShopImageImageHandler
import com.app.inventory.Models.UpdateShopImageExample.UpdateShopImageExample
import com.app.inventory.Models.UpdateShopModel.UpdateShopExample
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.util.Random

class UpdateShopPresenter(
    private val activity: Activity,
    private val updateShopView: UpdateShopView,
    private val updateShopActivity: UpdateShopActivity,
    private val bitmap: Bitmap,

    ) {
    private var imagePart: MultipartBody.Part? = null

    fun updateShop(
        name: String,
        address: String,
        phoneno: Int,

        ) {
        if (Utils.isNetworkConnected(activity!!)) {
            val id = CSPreferences.readString(activity, Utils.SID)
            val token = CSPreferences.readString(activity, Utils.TOKEN)
            updateShopView.showDialog(activity)
            WebServices.Factory1.getInstance()?.updateShop(
                token!!, id!!, name, address, phoneno, object : UpdateShopHandler {
                    override fun onSuccess(updateShopExample: UpdateShopExample) {
                        updateShopView.hideDialog()
                        if (updateShopExample != null) {
                            if (updateShopExample.isSuccess == true) {
                                if (updateShopExample.data.id.isNotEmpty()) {
                                    val id = updateShopExample.data.id
                                    CSPreferences.putString(
                                        activity,
                                        Utils.SNAME,
                                        updateShopExample.data.name
                                    )
                                    CSPreferences.putString(
                                        activity,
                                        Utils.SADDRES,
                                        updateShopExample.data.address
                                    )
                                    CSPreferences.putString(
                                        activity, Utils.SNO,
                                        updateShopExample.data.phoneno.toString()
                                    )

                                    uploadShopImage(bitmap, id)
                                    updateShopView?.showMessage(activity, updateShopExample.message)
                                } else {
                                    updateShopView?.showMessage(
                                        activity,
                                        updateShopExample.message
                                    )
                                }
                            } else {
                                updateShopView?.showMessage(activity, updateShopExample.message)
                            }
                        } else {
                            updateShopView?.showMessage(activity, updateShopExample.message)
                        }
                    }

                    override fun onError(message: String?) {
                        updateShopView.hideDialog()
                        updateShopView.showMessage(activity, message)
                    }
                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadShopImage(bitmap: Bitmap, id: String) {
        val id = CSPreferences.readString(activity, Utils.SID)
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

            updateShopView?.showDialog(activity)
            WebServices.Factory1.getInstance()
                ?.updateShopImageMethod(id!!, imagePart!!, object :
                    UpdateShopImageImageHandler {
                    override fun onSuccess(updateShopImageExample: UpdateShopImageExample?) {
                        updateShopView?.hideDialog()
                        if (updateShopImageExample != null) {
                            if (updateShopImageExample.isSuccess === true) {

                                updateShopView?.showMessage(activity, updateShopImageExample.data)
                                val intent = Intent(activity, ShopDetailsActivity::class.java)
                                activity.startActivity(intent)

                            } else {

                            }
                        } else {

                        }
                    }


                    override fun onError(message: String?) {
                        updateShopView?.hideDialog()
//                        updateShopView?.showMessage(activity, message)
                        Toast.makeText(activity, "image not upload", Toast.LENGTH_SHORT).show()

                    }
                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }

}