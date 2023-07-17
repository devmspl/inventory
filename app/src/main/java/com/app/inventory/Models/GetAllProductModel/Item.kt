package com.app.inventory.Models.GetAllProductModel

data class Item(
    val avatar: String,
    val code: String,
    val costprice: Long,
    val date: String,
    val description: String,
    val id: String,
    val productname: String,
    val quality: String,
    val sellingprice: Long
)