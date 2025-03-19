package com.example.myundivorcer.dbHelpers


import android.content.Context
import androidx.room.Room
import com.example.myundivorcer.dataClasses.ShopList
import com.example.myundivorcer.dataClasses.ShopListItem

class ShopListsDBHelper(context: Context) {

    private val db: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "shop_list_database"
    ).build()

    private val shopListDao = db.shopListDao()

    suspend fun getAllUserLists(): List<ShopList> {
        return shopListDao.getAllShopLists()
    }

    suspend fun insertShopList(shopList: ShopList) {
        shopListDao.insertShopList(shopList)
    }

    suspend fun updateShopList(shopList: ShopList) {
        shopListDao.updateShopList(shopList)
    }

    suspend fun deleteShopList(shopList: ShopList) {
        shopListDao.deleteShopList(shopList)
    }

    suspend fun getShopListById(id: Long): ShopList? {
        return shopListDao.getShopListById(id)
    }
}