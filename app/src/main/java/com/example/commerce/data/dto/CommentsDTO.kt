package com.example.commerce.data.dto

import com.google.gson.annotations.SerializedName

data class CommentsDTO(
    @SerializedName("created_at")
    val created_at: Long,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("productId")
    val productId: Int,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("userId")
    val userId: Int
)