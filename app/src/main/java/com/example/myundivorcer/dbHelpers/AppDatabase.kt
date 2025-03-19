package com.example.myundivorcer.dbHelpers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myundivorcer.dbHelpers.ShopList
import com.example.myundivorcer.dbHelpers.ShopListItem

@Database(entities = [ShopList::class, ShopListItem::class], version = 1)
@TypeConverters(ShopListItemConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shopListDao(): ShopListDao
    abstract fun RecipeDao(): RecipeDao
}