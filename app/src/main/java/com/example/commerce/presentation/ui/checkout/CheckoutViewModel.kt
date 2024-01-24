package com.example.commerce.presentation.ui.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerce.common.util.Resource
import com.example.commerce.data.dto.AddressDTO
import com.example.commerce.data.dto.OrderDTO
import com.example.commerce.data.dto.PaymentDTO
import com.example.commerce.data.repository.ApiRepoImpl
import com.example.commerce.domain.model.OrderModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val apiRepo : ApiRepoImpl
) : ViewModel() {

    private val _addressData = MutableLiveData<Resource<List<AddressDTO>>>()
    val addressData: LiveData<Resource<List<AddressDTO>>> get() = _addressData

    private val _paymentData = MutableLiveData<Resource<List<PaymentDTO>>>()
    val paymentData: LiveData<Resource<List<PaymentDTO>>> get() = _paymentData

    private val _orderData = MutableLiveData<Resource<OrderDTO>>()
    val orderData: LiveData<Resource<OrderDTO>> get() = _orderData

    var infoAddress = MutableLiveData<String>()
    var infoPayment = MutableLiveData<String>()


    fun getAddressUser(user_id : Int){
        viewModelScope.launch {
            val response = apiRepo.getAddressUser(user_id)
            response.collectLatest {
                _addressData.postValue(it)
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

    fun addOrder(orderModel: OrderModel){
        viewModelScope.launch {
            val response = apiRepo.addOrder(orderModel)
            response.collectLatest {
                _orderData.postValue(it)
            }
        }
    }
}