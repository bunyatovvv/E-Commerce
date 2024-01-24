package com.example.commerce.data.dto

data class PaymentDTO(
    val ccv: String,
    val created_at: Long,
    val exp: String,
    val holder: String,
    val id: Int,
    val number: String,
    val users_id: Int
)