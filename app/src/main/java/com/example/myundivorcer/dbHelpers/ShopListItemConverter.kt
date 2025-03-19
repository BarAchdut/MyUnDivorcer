package com.example.myundivorcer.dbHelpers

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.myundivorcer.dataClasses.ShopListItem

class ShopListItemConverter {

    @TypeConverter
    fun fromItemsList(items: List<ShopListItem>): String {
        val gson = Gson()
        return gson.toJson(items)
    }

    @TypeConverter
    fun toItemsList(data: String): List<ShopListItem> {
        val gson = Gson()
        val listType = object : TypeToken<List<ShopListItem>>() {}.type
        return gson.fromJson(data, listType)
    }
}