package com.example.commerce.domain.model

data class BasketModel(
    val image: String,
    val price: Double,
    val products_id: Int,
    val quantity: Int,
    val title: String
)