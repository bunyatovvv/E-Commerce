package com.example.commerce.presentation.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerce.common.util.Resource
import com.example.commerce.data.dto.AddressDTO
import com.example.commerce.data.dto.PaymentDTO
import com.example.commerce.data.repository.ApiRepoImpl
import com.example.commerce.data.service.remote.Api
import com.example.commerce.data.token.SessionManager
import com.example.commerce.domain.model.AddressModel
import com.example.commerce.domain.model.PaymentModel
import com.example.commerce.domain.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sp: SessionManager,
    private val api: Api,
    private val apiRepo : ApiRepoImpl
) : ViewModel() {

    private val _userData = MutableLiveData<UserModel?>()
    val userData: MutableLiveData<UserModel?> get() = _userData

    private val _addressData = MutableLiveData<Resource<List<AddressDTO>>>()
    val addressData: LiveData<Resource<List<AddressDTO>>> get() = _addressData

    private val _paymentData = MutableLiveData<Resource<List<PaymentDTO>>>()
    val paymentData: LiveData<Resource<List<PaymentDTO>>> get() = _paymentData

    private val _paymentPost = MutableLiveData<Resource<PaymentDTO>>()
    val paymentPost: LiveData<Resource<PaymentDTO>> get() = _paymentPost

    private val _singleAddress = MutableLiveData<Resource<AddressDTO>>()
    val singleAddress: LiveData<Resource<AddressDTO>> get() = _singleAddress



    init {
        getUser()
    }

    fun logout() {
        viewModelScope.launch {
            sp.removeAuthToken()
            _userData.postValue(null)
        }
    }

    fun getUser() {
        viewModelScope.launch {
            val response = api.getCurrentUser(sp.fetchAuthToken() ?: "")
            if (response.isSuccessful) {
                _userData.postValue(response.body())
            }
        }
    }

    fun getAddressUser(user_id : Int){
        viewModelScope.launch {
            val response = apiRepo.getAddressUser(user_id)
            response.collectLatest {
                _addressData.postValue(it)
            }
        }
    }

    fun addAddress(addressModel: AddressModel){
        viewModelScope.launch {
            apiRepo.addAddress(addressModel).collectLatest {
                _singleAddress.postValue(it)
            }
        }
    }

    fun getPaymentUser(user_id: Int){
        viewModelScope.launch {
            val response = apiRepo.getPaymentUser(user_id)
            response.collectLatest {
                _paymentData.postValue(it)
            }
        }
    }

    fun addPaymentUser(paymentModel: PaymentModel){
        viewModelScope.launch {
            val response = apiRepo.addPaymentUser(paymentModel)
            response.collectLatest {
                _paymentPost.postValue(it)
            }
        }
    }
}