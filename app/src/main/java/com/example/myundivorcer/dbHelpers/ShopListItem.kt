package com.example.myundivorcer.dbHelpers

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_list_items")
data class ShopListItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val count: Int,
    val unit: String,
    val checked: Boolean
)