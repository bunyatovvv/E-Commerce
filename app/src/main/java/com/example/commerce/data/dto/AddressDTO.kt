package com.example.commerce.data.dto

data class AddressDTO(
    val city: String,
    val created_at: Long,
    val id: Int,
    val state: String,
    val street_address: String,
    val users_id: Int,
    val zip: String
)