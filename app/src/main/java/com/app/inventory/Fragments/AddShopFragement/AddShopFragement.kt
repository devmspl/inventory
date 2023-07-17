package com.app.inventory.Fragments.AddShopFragement

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.app.inventory.Fragments.AddShopFragement.Presenter.AddShopPresenter
import com.app.inventory.Fragments.AddShopFragement.View.AddShopView
import com.app.inventory.R
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.FragmentAddShopFragementBinding
import java.io.IOException

class AddShopFragement : Fragment(), View.OnClickListener, AddShopView {
    private var binding: FragmentAddShopFragementBinding? = null
    private var addShopPresenter: AddShopPresenter? = null
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var bitmap: Bitmap? = null
    private var bundle: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddShopFragementBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        listeners()

        return view
    }

    private fun listeners() {
        binding?.btsubmit?.setOnClickListener(this)
        binding?.addimage?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btsubmit -> {
                val contactNumString = binding!!.etshopcontactnum?.text.toString()
                val contactNum: Int? = contactNumString.toIntOrNull()

                if (contactNum != null) {
                    addShopPresenter?.addShopMethod(
                        binding!!.etcompanyname?.text.toString(),
                        binding!!.etshopaddress?.text.toString(),
                        contactNum,
                        binding!!.etshopemail?.text.toString()
                    )
                }
            }

            R.id.addimage -> {
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
                bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, imageUri)
                addShopPresenter = AddShopPresenter(
                    activity as FragmentActivity, this, this, bitmap!!
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }

            binding?.addimage!!.setImageURI(imageUri)
            binding?.tvaddimg!!.setText("Added")
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