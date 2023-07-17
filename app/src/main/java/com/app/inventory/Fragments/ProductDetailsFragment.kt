package com.app.inventory.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.inventory.Activities.SearchProductActivity.SearchProductActivity
import com.app.inventory.R
import com.app.inventory.databinding.FragmentProductDetailsBinding
import com.bumptech.glide.Glide

class ProductDetailsFragment : Fragment(), View.OnClickListener, TextView.OnEditorActionListener {
    private lateinit var binding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        val view: View = binding.root

        val arguments = arguments
        if (arguments != null) {
            val productId = arguments.getString("_id")
            val productName = arguments.getString("productName")
            val avatar = arguments.getString("avatar")
            val code = arguments.getString("code")
            val description = arguments.getString("description")
            val quantity = arguments.getInt("quantity")
            val sellingprice = arguments.getInt("sellingprice")


            binding.sellingPrize.text = sellingprice.toString()
            binding.tvItemQuantity.setText(quantity.toString())
            binding.productNameee.text = productName
            binding.description.text = description
            binding.productCode.text = code

            Glide.with(requireContext())
                .load(avatar)
                .into(binding.productImage)
        }

        binding.backActivity.setOnClickListener(this)
        binding.tvItemQuantity.setOnEditorActionListener(this)
        binding.tvItemQuantity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateTotalPrice()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        return view
    }

    private fun calculateTotalPrice() {
        val quantity = binding.tvItemQuantity.text.toString().toIntOrNull() ?: 0
        val sellingPrice = binding.sellingPrize.text.toString().toIntOrNull() ?: 0
        val totalPrice = quantity * sellingPrice
        binding.totalPrice.text = totalPrice.toString()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.backActivity -> {
                findNavController().navigate(R.id.action_productDetailsFragment_to_homeFragment2)
            }
        }
    }

    override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
        if (p1 == EditorInfo.IME_ACTION_SEARCH) {
            if (binding.tvItemQuantity.text.isNotEmpty()) {
                performSearch()
                return true
            } else {
                Toast.makeText(activity, "Please enter a quantity", Toast.LENGTH_SHORT).show()
            }
        }
        return false
    }

    private fun performSearch() {
        binding.tvItemQuantity.clearFocus()
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.tvItemQuantity.windowToken, 0)
    }
}
