package com.example.commerce.data.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("created_at")
    val created_at: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("surname")
    val surname: String
)