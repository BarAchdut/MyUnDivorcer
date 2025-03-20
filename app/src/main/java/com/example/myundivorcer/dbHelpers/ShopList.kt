package com.example.myundivorcer.dbHelpers

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "shop_lists")
data class ShopList(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val members: List<String>,
    @TypeConverters(ShopListItemConverter::class) val items: List<ShopListItem>
)
