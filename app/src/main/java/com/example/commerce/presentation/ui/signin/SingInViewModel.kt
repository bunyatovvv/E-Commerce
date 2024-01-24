package com.example.commerce.presentation.ui.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerce.common.util.Resource
import com.example.commerce.common.util.Status
import com.example.commerce.data.dto.AuthToken
import com.example.commerce.data.dto.BasketDTO
import com.example.commerce.data.dto.UserDTO
import com.example.commerce.data.repository.ApiRepoImpl
import com.example.commerce.data.service.remote.Api
import com.example.commerce.data.token.SessionManager
import com.example.commerce.domain.model.LoginModel
import com.example.commerce.domain.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
    private var repo: ApiRepoImpl,
    private var api: Api,
    private var sp: SessionManager
) : ViewModel() {

    val token: MutableLiveData<AuthToken> = MutableLiveData()

    fun login(loginModel: LoginModel) {
        viewModelScope.launch() {
            val response = api.login(loginModel)
            if (response.isSuccessful) {
                token.postValue(response.body())
                response.body()?.let {
                    sp.saveAuthToken(it.authToken)
                }
            } else {
                Log.e("codeee", response.code().toString())
            }
        }
    }


}