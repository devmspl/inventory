package com.app.inventory.Fragments.ShopGetByUserIdFragment.Presenter

import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.app.inventory.Fragments.ShopGetByUserIdFragment.ShopgetByUserIdFragment
import com.app.inventory.Fragments.ShopGetByUserIdFragment.View.ShopGetByUserIdView
import com.app.inventory.R

class ShopGetByUserIdPresenter(
    private val activity: FragmentActivity,
    private val allViewofShopFragment: ShopgetByUserIdFragment,
    private val allViewofShopView: ShopGetByUserIdView,
) {
    fun handleGridItemClick(position: Int) {
        when (position) {
            0 -> navigateToViewInventeryFragment()
            1 -> navigateToProductGetByIdFragment()
            2 -> navigateToViewCustomerFragment()
            3 -> navigateToLiquidationFragment()
            4 -> navigateToOrdersFragment()
            5 -> navigateToBalanceFragment()
            else -> allViewofShopView.showMessage(activity, "Invalid position")
        }
    }

    private fun navigateToViewInventeryFragment() {
        allViewofShopFragment.findNavController()
            .navigate(R.id.action_allViewofShopFragment_to_inventoryFragment)
    }


    private fun navigateToProductGetByIdFragment() {
           allViewofShopFragment.findNavController()
                .navigate(R.id.action_allViewofShopFragment_to_productGetByIdFragment)
    }
    private fun navigateToViewCustomerFragment() {
        allViewofShopFragment.findNavController()
            .navigate(R.id.action_allViewofShopFragment_to_viewCustomerFragment)
    }

    private fun navigateToLiquidationFragment() {
        allViewofShopFragment.findNavController()
            .navigate(R.id.action_allViewofShopFragment_to_bookKeppingActivity)
    }

    private fun navigateToOrdersFragment() {
        allViewofShopFragment.findNavController()
            .navigate(R.id.action_allViewofShopFragment_to_productsFragment2)
    }

    private fun navigateToBalanceFragment() {
        allViewofShopFragment.findNavController()
            .navigate(R.id.action_allViewofShopFragment_to_balanceFragment)
    }
}

