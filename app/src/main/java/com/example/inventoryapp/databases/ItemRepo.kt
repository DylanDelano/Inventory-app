package com.example.inventoryapp.databases

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class ItemRepo(private val itemDao: ItemDao) {
    val getAllItem:LiveData<List<Itemsdata>> = itemDao.getAllItem()

    fun newItem(itemsdata:Itemsdata){
        itemDao.addItem(itemsdata)
    }

     fun createItem(itemsdata: Itemsdata){
        itemDao.updateItem(itemsdata)
    }

     fun deleteItems(itemsdata: Itemsdata){
         itemDao.deleteItem(itemsdata)
     }

    fun deleteAllItems(){
        itemDao.deleteAllItems()
    }
}