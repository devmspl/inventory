package com.app.inventory.Models.GetAllShopModel

data class GetAllShopModel(
    val isSuccess: Boolean,
    val items: List<Item>,
    val pageNo: Int,
    val pageSize: Int,
    val statusCode: Int,
    val total: Int
)