package com.example.commerce.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class ProductDTO(
    val brand: String,
    val categories_id: Int,
    val created_at: Long,
    val description: String,
    val discountPercentage: Int,
    val id: Int,
    val images: List<String>,
    val price: Double,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
) : Parcelable