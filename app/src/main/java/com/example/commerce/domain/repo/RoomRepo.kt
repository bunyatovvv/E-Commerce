package com.example.commerce.domain.repo

import com.example.commerce.common.util.Resource
import com.example.commerce.data.dto.local.FavoritesDTO
import kotlinx.coroutines.flow.Flow

interface RoomRepo {

    suspend fun getAllFavorites(): Flow<Resource<List<FavoritesDTO>>>

    suspend fun insertFavorite(favoritesDTO: FavoritesDTO)

    suspend fun deleteFavorite(favoritesDTO: FavoritesDTO)
}