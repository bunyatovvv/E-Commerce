package com.example.commerce.domain.model

data class BasketPutModel (
    val user_id : Int,
    val products: List<BasketModel>,
    val total_price : Double
)