package com.app.inventory.Fragments.AddShopFragement.Presenter

import android.graphics.Bitmap
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.app.inventory.Api.WebServices
import com.app.inventory.Fragments.AddShopFragement.AddShopFragement
import com.app.inventory.Fragments.AddShopFragement.View.AddShopView
import com.app.inventory.Handler.AddShopHandler
import com.app.inventory.Handler.UploadProfileImageHandler
import com.app.inventory.Models.AddShopModel.AddShopModel
import com.app.inventory.Models.UploadshopImageModel.UploadShopImageExample
import com.app.inventory.R
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.util.Random

class AddShopPresenter(
    private val activity: FragmentActivity,
    private val addShopView: AddShopView,
    private val addShopFragement: AddShopFragement,
    private val bitmap: Bitmap


    ) {
    private var id: RequestBody? = null
    private var imagePart: MultipartBody.Part? = null

    fun addShopMethod(name: String, address: String, phoneno: Int, email: String) {
        if (Utils.isNetworkConnected(activity!!)) {
            val owner = CSPreferences.readString(activity, Utils.USERID)
            addShopView?.showDialog(activity)
            WebServices.Factory1.getInstance()
                ?.addShopMethod(name, address, phoneno, email, owner!!, object :
                    AddShopHandler {
                    override fun onSuccess(addShopExample: AddShopModel?, owner: String?) {
                        addShopView?.hideDialog()
                        if (addShopExample != null) {
                            if (addShopExample.isSuccess === true) {
                                val shopId = addShopExample.data._id
                                CSPreferences.putString(activity, Utils.CHECKSHOPSTATUS, "true")
                               CSPreferences.putString(activity,Utils.SID,addShopExample.data._id)
                               CSPreferences.putString(activity,Utils.SADDRES,addShopExample.data.address)
                               CSPreferences.putString(activity,Utils.SEMAIL,addShopExample.data.email)
                               CSPreferences.putString(activity,Utils.SOWNER,addShopExample.data.owner)
                               CSPreferences.putString(activity,Utils.SNO,
                                   addShopExample.data.phoneno.toString()
                               )
                               CSPreferences.putString(activity,Utils.SNAME,addShopExample.data.name)
                                uploadshopImage(bitmap, shopId)

                                addShopView?.showMessage(activity, addShopExample.message)
                            } else {
                                addShopView?.showMessage(activity, addShopExample.message)
                            }
                        } else {
                            addShopView?.showMessage(activity, addShopExample?.message)
                        }
                    }


                    override fun onError(message: String?) {
                        addShopView?.hideDialog()
                        addShopView?.showMessage(activity, message)
                    }
                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }
    }

    //uploadshopimage
    fun uploadshopImage(photo: Bitmap, SHOPID: String) {
        val shopId = CSPreferences.readString(activity, Utils.SHOPID)
        if (photo != null) {
            val stream = ByteArrayOutputStream()
            photo.compress(Bitmap.CompressFormat.JPEG, 40, stream)
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

            addShopView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.uploadProfileImage(SHOPID!!, imagePart!!, object :
                UploadProfileImageHandler {
                override fun onSuccess(uploadShopImageExample: UploadShopImageExample?) {
                    addShopView?.hideDialog()
                    if (uploadShopImageExample != null) {
                        if (uploadShopImageExample.isSuccess === true) {
                            NavHostFragment.findNavController(addShopFragement)
                                .navigate(R.id.action_addShopFragement2_to_homeNavActivity)
                            addShopView?.showMessage(activity, uploadShopImageExample.data)

                        } else {
                            addShopView?.showMessage(activity, uploadShopImageExample.data)
                        }
                    } else {
                        addShopView?.showMessage(activity, uploadShopImageExample?.data)
                    }
                }


                override fun onError(message: String?) {
                    addShopView?.hideDialog()
                    addShopView?.showMessage(activity, message)
                }
            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }


}