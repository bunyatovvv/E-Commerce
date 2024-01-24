package com.example.commerce.presentation.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerce.common.util.Resource
import com.example.commerce.data.dto.OrderDTO
import com.example.commerce.domain.repo.ApiRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val apiRepo : ApiRepo
) : ViewModel() {

    private val _orderData= MutableLiveData<Resource<List<OrderDTO>>>()
    val orderData: LiveData<Resource<List<OrderDTO>>> get()=_orderData

    val userId : MutableLiveData<Int> = MutableLiveData(0)

    fun getOrdersUser(userId : Int) {
        viewModelScope.launch {
            val response = apiRepo.getOrdersUser(userId)
            response.collectLatest {
                _orderData.postValue(it)
            }
        }
    }
}