package com.example.commerce.domain.model

data class OrderModel(
    val address : String,
    val users_id : Int,
    val products : List<BasketModel>
)