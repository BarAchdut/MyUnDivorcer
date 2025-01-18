package com.example.myundivorcer.dataClasses

data class Recipe(
    val id: String? = null,
    var name: String,
    var items: List<ShopListItem>?,
    var members: List<String>,
    val procedure: String,
) {
    override fun toString(): String {
        return name
    }
}