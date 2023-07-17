package com.app.inventory.Models.AddShopModel

data class Data(
    val _id: String,
    val address: String,
    val avatar: String,
    val customers: List<Any>,
    val email: String,
    val name: String,
    val owner: String,
    val phoneno: Int
)