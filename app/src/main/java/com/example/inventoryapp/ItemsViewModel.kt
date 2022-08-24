package com.example.inventoryapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.inventoryapp.databases.ItemDatabase
import com.example.inventoryapp.databases.ItemRepo
import com.example.inventoryapp.databases.Itemsdata
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ItemsViewModel(application:Application): AndroidViewModel(application) {
    private val itemRepo:ItemRepo
    val getAllItem: LiveData<List<Itemsdata>>

     init{
         val itemDao = ItemDatabase.getDatabase(application).itemDao()
         itemRepo = ItemRepo(itemDao)
         this.getAllItem = itemDao.getAllItem()
     }

    fun newItem(itemsdata: Itemsdata){
        viewModelScope.launch(Dispatchers.IO){
            itemRepo.newItem(itemsdata)
        }
    }

    fun createItem(itemsdata: Itemsdata){
        viewModelScope.launch(Dispatchers.IO){
            itemRepo.createItem(itemsdata)
        }
    }

    fun deleteItems(itemsdata: Itemsdata){
        viewModelScope.launch(Dispatchers.IO){
            itemRepo.deleteItems(itemsdata)
        }
    }

    fun deleteAllItems(){
        viewModelScope.launch(Dispatchers.IO){
            itemRepo.deleteAllItems()
        }

    }    }