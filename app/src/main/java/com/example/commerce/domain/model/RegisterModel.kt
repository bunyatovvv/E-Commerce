package com.example.commerce.domain.model

data class RegisterModel(
    val email: String,
    val name: String,
    val password: String,
    val surname: String
)