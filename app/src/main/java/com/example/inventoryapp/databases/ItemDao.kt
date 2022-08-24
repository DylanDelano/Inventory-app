package com.example.inventoryapp.databases

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {
    @Query("SELECT * FROM Itemsdata ORDER BY id ASC")
    fun getAllItem():LiveData<List<Itemsdata>>

    @Insert
    fun addItem(itemsdata: Itemsdata)

    @Update
    fun updateItem(itemsdata: Itemsdata)

    @Delete
    fun deleteItem(itemsdata: Itemsdata)

    @Query("DELETE FROM Itemsdata")
    fun deleteAllItems()
}