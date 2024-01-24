package com.example.commerce.presentation.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerce.data.repository.ApiRepoImpl
import com.example.commerce.data.service.remote.Api
import com.example.commerce.data.token.SessionManager
import com.example.commerce.domain.model.RegisterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repo: ApiRepoImpl,
    private val api: Api,
    private val sp: SessionManager
) : ViewModel() {

    private var _authToken: MutableLiveData<String> = MutableLiveData()
    val authToken = _authToken

    fun register(registerModel: RegisterModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.register(registerModel)
            if (response.isSuccessful) {
                _authToken.postValue(response.body()?.authToken)
                response.body()?.let {
                    sp.saveAuthToken(it.authToken)
                }
            }
        }
    }
}