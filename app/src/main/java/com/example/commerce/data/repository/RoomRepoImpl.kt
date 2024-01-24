package com.example.commerce.data.repository

import com.example.commerce.common.util.Resource
import com.example.commerce.common.util.Status
import com.example.commerce.data.dto.local.FavoritesDTO
import com.example.commerce.data.source.RoomSource
import com.example.commerce.domain.repo.RoomRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RoomRepoImpl @Inject constructor(
    private val roomSource: RoomSource
) : RoomRepo {

    override suspend fun getAllFavorites(): Flow<Resource<List<FavoritesDTO>>> = flow {
        emit(Resource.loading(null))
        val response = roomSource.getAllFavorites()
        when (response.status) {
            Status.SUCCESS -> emit(Resource.success(response.data))
            Status.ERROR -> emit(Resource.error(response.message ?: "error room get all", null))
            else -> {
                emit(Resource.loading(emptyList()))
            }
        }
    }

    override suspend fun insertFavorite(favoritesDTO: FavoritesDTO) {
        roomSource.insertFavorite(favoritesDTO)
    }

    override suspend fun deleteFavorite(favoritesDTO: FavoritesDTO) {
        roomSource.deleteFavorite(favoritesDTO)
    }
}