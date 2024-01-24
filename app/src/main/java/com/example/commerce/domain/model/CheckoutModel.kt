package com.example.commerce.domain.model

data class CheckoutModel(
    val subtotal : Int,
    val shipping : Int,
    val tax: Int = 0,
    val total : Int
)