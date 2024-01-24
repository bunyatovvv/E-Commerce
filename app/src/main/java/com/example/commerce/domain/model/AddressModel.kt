package com.example.commerce.domain.model

data class AddressModel(
    val city: String,
    val state: String,
    val street_address: String,
    val users_id: Int,
    val zip: String
)