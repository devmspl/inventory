package com.app.inventory.Activities.Activiti

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.inventory.Adapter.OrderDetailsAdapter
import com.app.inventory.Models.OrderDetailsModel
import com.app.inventory.R

class OrderDetailsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var orderDetailsAdapter: OrderDetailsAdapter
    private lateinit var orderDetailsList: List<OrderDetailsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        recyclerView = findViewById(R.id.recycleViewOrder)

        orderDetailsList = listOf(
            OrderDetailsModel(R.drawable.facewash, "Face Wash", "Category 1", "10", "300.00$"),
            OrderDetailsModel(R.drawable.facewash, "Face Wash", "Category 1", "10", "300.00$"),
            OrderDetailsModel(R.drawable.facewash, "Face Wash", "Category 1", "10", "300.00$"),
            OrderDetailsModel(R.drawable.facewash, "Face Wash", "Category 1", "10", "300.00$"),
            OrderDetailsModel(R.drawable.facewash, "Face Wash", "Category 1", "10", "300.00$"),
            OrderDetailsModel(R.drawable.facewash, "Face Wash", "Category 1", "10", "300.00$"),
            OrderDetailsModel(R.drawable.facewash, "Face Wash", "Category 1", "10", "300.00$"),

        )

        orderDetailsAdapter = OrderDetailsAdapter(orderDetailsList, this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = orderDetailsAdapter
    }
}