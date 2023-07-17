import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.app.inventory.Activities.ProductDetailsActivity.ProductDetailsActivity
import com.app.inventory.Fragments.ProductGetByidFragment.ProductGetByIdFragment
import com.app.inventory.Models.ProductGetById.Data

import com.app.inventory.R
import com.bumptech.glide.Glide

class ProductGetByIdAdapter(
    private val activity: FragmentActivity,
    private val productGetByIdFragment: ProductGetByIdFragment,
    private val productList: List<Data>,

    ) : BaseAdapter() {
    private val layoutInflater: LayoutInflater =
        activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return productList.size
    }

    override fun getItem(position: Int): Any {
        return productList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.viewproductbyshopid_grid, null)
        }

        val viewproductName: TextView = convertView!!.findViewById(R.id.viewproductName)
        val viewproductImg: ImageView = convertView.findViewById(R.id.viewproductImg)

        val productData = productList[position]

        productData.avatar?.let {
            Glide.with(activity)
                .load(it)
                .placeholder(R.drawable.biscuit)
                .into(viewproductImg)
        }

        viewproductName.text = productData.productname

        viewproductImg.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            val bundle = Bundle()
            intent.putExtra("single_id",productData._id)
            bundle.putString("id", productData._id)
            bundle.putString("avatar", productData.avatar)
            bundle.putString("code", productData.code)
            bundle.putString("description", productData.description)
            bundle.putString("date", productData.date)
            bundle.putString("quantity", productData.quantity)
            bundle.putString("productname", productData.productname)
            bundle.putString("sellingprice", productData.sellingprice.toString())
            bundle.putString("costprice", productData.costprice.toString())
            intent.putExtras(bundle)
            activity.startActivity(intent)
        }

        return convertView
    }
}
