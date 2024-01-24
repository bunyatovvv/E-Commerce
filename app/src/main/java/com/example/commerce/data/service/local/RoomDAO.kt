package com.example.commerce.data.service.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.commerce.data.dto.local.FavoritesDTO

@Dao
interface RoomDAO {

    @Query("SELECT * FROM favorites_table")
    suspend fun getAllFavorites(): List<FavoritesDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoritesDTO: FavoritesDTO)

    @Delete
    suspend fun deleteFavorite(favoritesDTO: FavoritesDTO)
}