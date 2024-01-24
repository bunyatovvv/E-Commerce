package com.example.commerce.domain.model

data class PaymentModel(
    val number : String,
    val ccv : String,
    val exp : String,
    val holder : String,
    val users_id : Int
)