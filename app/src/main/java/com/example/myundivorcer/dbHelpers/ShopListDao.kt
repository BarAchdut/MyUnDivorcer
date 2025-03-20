package com.example.myundivorcer.dbHelpers

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myundivorcer.dataClasses.ShopList

@Dao
interface ShopListDao {

    @Insert
    suspend fun insertShopList(shopList: ShopList)

    @Update
    suspend fun updateShopList(shopList: ShopList)

    @Delete
    suspend fun deleteShopList(shopList: ShopList)

    @Query("SELECT * FROM shop_lists WHERE id = :id")
    suspend fun getShopListById(id: Long): ShopList?

    @Query("SELECT * FROM shop_lists")
    suspend fun getAllShopLists(): List<ShopList>
}