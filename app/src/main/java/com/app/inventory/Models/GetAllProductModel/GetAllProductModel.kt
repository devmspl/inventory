package com.app.inventory.Models.GetAllProductModel

data class GetAllProductModel(
    val isSuccess: Boolean,
    val items: List<Item>,
    val pageNo: Int,
    val pageSize: Int,
    val statusCode: Int,
    val total: Int
)