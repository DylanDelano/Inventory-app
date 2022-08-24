package com.example.inventoryapp.databases

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Itemsdata(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo(name= "Item ")
    val Item:String,

    @ColumnInfo(name= "Price ")
    val Price: Int,

    @ColumnInfo(name = "Quantity")
    val Quantity: Int,
):Parcelable
