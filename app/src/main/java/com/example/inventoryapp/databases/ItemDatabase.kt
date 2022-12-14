package com.example.inventoryapp.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Itemsdata::class], version = 1, exportSchema = false)
abstract class ItemDatabase:RoomDatabase() {
    abstract fun itemDao():ItemDao

    companion object{
        @Volatile
        private var INSTANCE:ItemDatabase? = null
        fun getDatabase(context:Context):ItemDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,ItemDatabase::class.java,"item_database")
                    .build()
                INSTANCE = instance
                return instance
            }


        }

    }
}