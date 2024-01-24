package com.example.commerce.domain.model

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
) {
}