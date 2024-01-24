package com.example.commerce.data.mapper

import com.example.commerce.data.dto.ProductDTO
import com.example.commerce.data.dto.local.FavoritesDTO
import com.example.commerce.domain.model.BasketModel

fun ProductDTO.toFavoritesDTO() : FavoritesDTO{
    return FavoritesDTO(
        title = title,
        price = price,
        image = thumbnail,
        productId = id
    )
}
