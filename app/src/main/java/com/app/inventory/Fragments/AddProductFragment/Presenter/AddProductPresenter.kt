package com.app.inventory.Fragments.AddProductFragment.Presenter

import android.graphics.Bitmap
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.app.inventory.Api.WebServices
import com.app.inventory.Fragments.AddProductFragment.AddProductFragment
import com.app.inventory.Fragments.AddProductFragment.View.AddProductView
import com.app.inventory.Handler.AddProductHandler
import com.app.inventory.Handler.UploadProductImageHandler
import com.app.inventory.Models.AddProductModel.AddProductExample
import com.app.inventory.Models.UploadProductImage.UploadProductImageModel
import com.app.inventory.R
import com.app.inventory.Utlis.CSPreferences
import com.app.inventory.Utlis.Utils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.util.Random

class AddProductPresenter(
    private val activity: FragmentActivity,
    private val addProductView: AddProductView,
    private val addProductFragment: AddProductFragment,
    private val bitmap: Bitmap,
) {
    private var id: RequestBody? = null
    private var imagePart: MultipartBody.Part? = null

    fun addProductMethod(
        productname: String, description: String, quanlity: String, date: String, costprice: String,
        sellingprice: String,
    ) {
        if (Utils.isNetworkConnected(activity!!)) {
            var shopidd = CSPreferences.readString(activity, Utils.SID)

            addProductView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.addProudctMethod(productname, description, quanlity,
                date, costprice, sellingprice, shopidd!!, object :
                    AddProductHandler {
                    override fun onSuccess(addProductExample: AddProductExample?) {
                        addProductView?.hideDialog()
                        if (addProductExample != null) {
                            if (addProductExample.isSuccess === true) {
                                val productId = addProductExample.data._id
                                CSPreferences.putString(activity, Utils.CHECKPRODUCTSTATUS, "true")
                                CSPreferences.putString(
                                    activity,
                                    Utils.PRODUCTID, addProductExample.data._id
                                )
                                uploadproductImage(bitmap, productId)
                                addProductView?.showMessage(activity, addProductExample.message)
                            } else {
                                addProductView?.showMessage(activity, addProductExample.message)
                            }
                        } else {
                            addProductView?.showMessage(activity, addProductExample?.message)
                        }
                    }

                    override fun onError(message: String?) {
                        addProductView?.hideDialog()
                        addProductView?.showMessage(activity, message)
                    }
                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }  //uploadproductimage

    fun uploadproductImage(photo: Bitmap, PRODUCTID: String) {
        val productId = CSPreferences.readString(activity, Utils.PRODUCTID)
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

            addProductView?.showDialog(activity)
            WebServices.Factory1.getInstance()
                ?.uploadProductImage(PRODUCTID!!, imagePart!!, object :
                    UploadProductImageHandler {
                    override fun onSuccess(uploadProductImageModel: UploadProductImageModel?) {
                        addProductView?.hideDialog()
                        if (uploadProductImageModel != null) {
                            if (uploadProductImageModel.isSuccess === true) {
                                val navController =
                                    NavHostFragment.findNavController(addProductFragment)
                                navController.navigate(R.id.action_addProductFragment_to_allViewofShopFragment)
                                addProductView?.showMessage(activity, uploadProductImageModel.data)

                            } else {
                                addProductView?.showMessage(activity, uploadProductImageModel.data)
                            }
                        } else {
                            addProductView?.showMessage(activity, uploadProductImageModel?.data)
                        }
                    }


                    override fun onError(message: String?) {
                        addProductView?.hideDialog()
                        addProductView?.showMessage(activity, message)
                    }
                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }

}