package com.example.myundivorcer.dbHelpers

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    @TypeConverters(ShopListItemConverter::class) val items: List<ShopListItem>,
    val members: List<String>,
    val procedure: String
)