package com.example.commerce.data.dto.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class FavoritesDTO(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo("title")
    val title : String,

    @ColumnInfo("price")
    val price : Double,

    @ColumnInfo("image")
    val image : String,

    @ColumnInfo("product_id")
    val productId : Int
)