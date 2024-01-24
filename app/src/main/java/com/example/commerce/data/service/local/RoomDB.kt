package com.example.commerce.data.service.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.commerce.data.dto.local.FavoritesDTO

@Database(entities = [FavoritesDTO::class], version = 1, exportSchema = true)
abstract class RoomDB : RoomDatabase() {

    abstract fun getDao(): RoomDAO
}