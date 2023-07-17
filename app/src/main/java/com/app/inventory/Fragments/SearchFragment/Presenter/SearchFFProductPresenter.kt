import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.inventory.Adapter.SearchProductAdapter
import com.app.inventory.Api.WebServices
import com.app.inventory.Fragments.SearchFragment.SearchProductFragment
import com.app.inventory.Fragments.SearchFragment.View.SearchFFProductView
import com.app.inventory.Handler.SearchProductHandler
import com.app.inventory.Models.SearchproductModel.SearchproductExample
import com.app.inventory.Utlis.Utils

class SearchFFProductPresenter(
    private val activity: FragmentActivity,
    private val searchFFProductView: SearchFFProductView,
    private val searchProductFragment: SearchProductFragment,
    private val gridView: GridView?,
    private val arguments: Bundle?
) {
    private var searchProductAdapter: SearchProductAdapter? = null

    fun searchProduct() {
        if (Utils.isNetworkConnected(activity!!)) {
            val shopId = arguments?.getString("shopid")
            val searchValue = arguments?.getString("search")
            searchFFProductView.showDialog(activity)
            WebServices.Factory1.getInstance()?.searchProductByShopIdMethod(
                shopId!!, searchValue!!, "", "", object : SearchProductHandler {
                    override fun onSuccess(searchproductExample: SearchproductExample) {
                        searchFFProductView.hideDialog()
                        if (searchproductExample != null) {
                            if (searchproductExample.isSuccess == true) {
                                if (searchproductExample.data.isNotEmpty()) {
                                    val filteredProducts = searchproductExample.data.filter {
                                        it.productname.contains(searchValue, ignoreCase = true)
                                    }
                                    searchProductAdapter = SearchProductAdapter(
                                        activity,
                                        searchProductFragment,
                                        filteredProducts
                                    )
                                    gridView?.adapter = searchProductAdapter
                                } else {
                                    Toast.makeText(activity, "No Product", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                // searchProductView?.showMessage(activity, getProductBySearchExample.message)
                            }
                        } else {
                            // searchProductView?.showMessage(activity, getProductBySearchExample?.message)
                        }
                    }

                    override fun onError(message: String?) {
                        searchFFProductView.hideDialog()
                        searchFFProductView.showMessage(activity, message)
                    }
                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
        }
    }
}
