package com.example.commerce.data.dto

import com.example.commerce.domain.model.BasketModel

data class BasketDTO(
    val created_at: Long,
    val id: Int,
    val products: List<BasketModel>,
    val total_price: Int,
    val users_id: Int
)