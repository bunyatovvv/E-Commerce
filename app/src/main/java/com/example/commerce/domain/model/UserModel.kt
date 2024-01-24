package com.example.commerce.domain.model

data class UserModel(
    val created_at: Long,
    val email: String,
    val id: Int,
    val name: String,
    val photo: String,
    val surname: String
)