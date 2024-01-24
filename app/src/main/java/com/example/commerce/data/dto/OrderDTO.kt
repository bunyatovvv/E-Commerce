package com.example.commerce.data.dto

import com.example.commerce.domain.model.BasketModel
import java.io.Serializable

data class OrderDTO(
    val address: String,
    val confirmed: Boolean,
    val created_at: Long,
    val delivered: Boolean,
    val id: Int,
    val products: List<BasketModel>,
    val placed: Boolean,
    val shipped: Boolean,
    val users_id: Int
) : Serializable