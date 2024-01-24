package com.example.commerce.data.source

import com.example.commerce.data.dto.local.FavoritesDTO

interface RoomSource {

    suspend fun getAllFavorites(): com.example.commerce.common.util.Resource<List<FavoritesDTO>>

    suspend fun insertFavorite(favoritesDTO: FavoritesDTO)

    suspend fun deleteFavorite(favoritesDTO: FavoritesDTO)
}