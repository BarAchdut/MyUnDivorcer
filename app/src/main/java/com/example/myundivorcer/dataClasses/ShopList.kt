package com.example.myundivorcer.dataClasses

data class ShopList(
    val id: String? = null,
    var name: String,
    var items: List<ShopListItem>?,
    var members: List<String>,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var imageUri: String? = null
) {
    override fun toString(): String {
        return name
    }
}