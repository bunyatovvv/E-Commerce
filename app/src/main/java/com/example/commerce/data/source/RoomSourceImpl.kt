package com.example.commerce.data.source

import com.example.commerce.data.dto.local.FavoritesDTO
import com.example.commerce.data.service.local.RoomDAO
import javax.inject.Inject

class RoomSourceImpl @Inject constructor(
    private val dao: RoomDAO
) : RoomSource {

    override suspend fun getAllFavorites(): com.example.commerce.common.util.Resource<List<FavoritesDTO>> {
        return try {
            com.example.commerce.common.util.Resource.success(dao.getAllFavorites())
        } catch (e: Exception) {
            com.example.commerce.common.util.Resource.error(e.localizedMessage, null)
        }
    }

    override suspend fun insertFavorite(favoritesDTO: FavoritesDTO) {
        dao.insertFavorite(favoritesDTO)
    }

    override suspend fun deleteFavorite(favoritesDTO: FavoritesDTO) {
        dao.deleteFavorite(favoritesDTO)
    }
}