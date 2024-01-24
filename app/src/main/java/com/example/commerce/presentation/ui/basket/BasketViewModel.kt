package com.example.commerce.presentation.ui.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerce.common.util.Resource
import com.example.commerce.data.dto.BasketDTO
import com.example.commerce.data.repository.ApiRepoImpl
import com.example.commerce.domain.model.BasketModel
import com.example.commerce.domain.model.BasketPostModel
import com.example.commerce.domain.model.CheckoutModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val apiRepo: ApiRepoImpl
): ViewModel() {

    private val _basketData = MutableLiveData<Resource<BasketDTO>>()
    val basketData : LiveData<Resource<BasketDTO>> get() = _basketData

    private val _addBasketData = MutableLiveData<Resource<BasketDTO>>()
    val addBasketData : LiveData<Resource<BasketDTO>> get() = _addBasketData

    val checkOutModel = MutableLiveData<CheckoutModel>()


    fun getBasketUser(userId: Int){
        viewModelScope.launch{
            val response = apiRepo.getBasketUser(userId)
            response.collectLatest {
                _basketData.postValue(it)
            }
        }
    }

    fun postBasketUser(basketPostModel: BasketPostModel){
        viewModelScope.launch {
            val response = apiRepo.postBasketUser(basketPostModel)
            response.collectLatest {
                _addBasketData.postValue(it)
            }
        }
    }

    fun deleteBasket(userId: Int){
        viewModelScope.launch {
            apiRepo.deleteBasket(userId)
            getBasketUser(userId)
        }
    }
}