package com.example.commerce.common

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerce.common.util.Resource
import com.example.commerce.data.dto.CategoriesDTO
import com.example.commerce.data.dto.UserDTO
import com.example.commerce.data.repository.ApiRepoImpl
import com.example.commerce.data.service.remote.Api
import com.example.commerce.data.token.SessionManager
import com.example.commerce.domain.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val apiRepo: ApiRepoImpl,
    private val api: Api,
    private val sp: SessionManager
) : ViewModel() {

    private val _userData=MutableLiveData<Resource<List<UserDTO>>>()
    val userData: LiveData<Resource<List<UserDTO>>> get()=_userData

    val user: MutableLiveData<UserModel> = MutableLiveData()
    val email: MutableLiveData<String> = MutableLiveData()

    init {
        getAllUsers()
        getCurrentUser()
    }

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiRepo.getAllUsers()
            response.collectLatest {
                _userData.postValue(it)
            }
        }
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            val response = api.getCurrentUser("Bearer ${sp.fetchAuthToken()}")
            if (response.isSuccessful) {
                user.postValue(response.body())
            } else {
                Log.e("user error", response.message().toString())
            }
        }
    }
}