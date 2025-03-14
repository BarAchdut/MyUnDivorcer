package com.example.myundivorcer.dataClasses

data class ShopListItem(
    val title: String = "",
    val count: Int = 1,
    val unit: String = "יחידות",
    var checked: Boolean = false,
    var imageUri: String? = null  // Added image URI field
)