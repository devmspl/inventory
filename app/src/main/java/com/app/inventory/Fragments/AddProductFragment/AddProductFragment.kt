package com.app.inventory.Fragments.AddProductFragment

import android.app.Activity
import android.app.DatePickerDialog
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
import com.app.inventory.Fragments.AddProductFragment.Presenter.AddProductPresenter
import com.app.inventory.Fragments.AddProductFragment.View.AddProductView
import com.app.inventory.R
import com.app.inventory.Utlis.Utils
import com.app.inventory.databinding.FragmentAddProductBinding
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddProductFragment : Fragment(), View.OnClickListener, AddProductView {
    private var binding: FragmentAddProductBinding? = null
    private var addProductPresenter: AddProductPresenter? = null
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var bitmap: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddProductBinding.inflate(inflater, container, false)
        val view: View = binding!!.root

        listeners()

        return view
    }

    private fun listeners() {
        binding?.tvdate?.setOnClickListener(this)
        binding?.btnaddproduct?.setOnClickListener(this)
        binding?.addimagepro?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tvdate -> {
                val c = Calendar.getInstance()
                var mYear = c[Calendar.YEAR]
                var mMonth = c[Calendar.MONTH]
                var mDay = c[Calendar.DAY_OF_MONTH]
                val datePickerDialog = DatePickerDialog(
                    activity!!,
                    { view, year, monthOfYear, dayOfMonth ->
                        val selectedDate = Calendar.getInstance()
                        selectedDate.set(Calendar.YEAR, year)
                        selectedDate.set(Calendar.MONTH, monthOfYear)
                        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                        val formattedDate = dateFormat.format(selectedDate.time)

                        binding?.tvdate?.text = formattedDate
                    },
                    mYear,
                    mMonth,
                    mDay
                )
                datePickerDialog.show()
                datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            }

            R.id.btnaddproduct -> {
                addProductPresenter?.addProductMethod(
                    binding!!.etproductname.text.toString(),
                    binding!!.etdescription.text.toString(),
                    binding!!.etquality.text.toString(),
                    binding!!.tvdate.text.toString(),
                    binding!!.etprice.text.toString(),
                    binding!!.etsellingprice.text.toString()
                )
            }

            R.id.addimagepro -> {
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
                addProductPresenter = AddProductPresenter(
                    activity as FragmentActivity, this, this, bitmap!!
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }

            binding?.addimagepro!!.setImageURI(imageUri)
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