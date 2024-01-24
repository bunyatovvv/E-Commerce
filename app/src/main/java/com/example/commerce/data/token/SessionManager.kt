package com.example.commerce.data.token

import android.content.SharedPreferences
import javax.inject.Inject

class SessionManager @Inject constructor(var sp: SharedPreferences) {

    companion object {

        const val USER_TOKEN = "user_token"
    }

    fun saveAuthToken(token: String) {
        val editor = sp.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return sp.getString(USER_TOKEN, null)
    }

    fun removeAuthToken() {
        val editor = sp.edit()
        editor.remove(USER_TOKEN)
        editor.apply()
    }
}