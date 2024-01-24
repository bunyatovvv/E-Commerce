package com.example.commerce.presentation.ui.single_product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerce.common.util.Resource
import com.example.commerce.data.dto.BasketDTO
import com.example.commerce.data.dto.CommentsDTO
import com.example.commerce.data.dto.ProductDTO
import com.example.commerce.data.dto.local.FavoritesDTO
import com.example.commerce.data.repository.ApiRepoImpl
import com.example.commerce.data.repository.RoomRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleProductViewModel @Inject constructor(
    private val apiRepo: ApiRepoImpl,
    private val roomRepo: RoomRepoImpl
) : ViewModel() {

    private val _productData = MutableLiveData<Resource<ProductDTO>>()
    val productData: LiveData<Resource<ProductDTO>> get() = _productData

    private val _commentsData = MutableLiveData<Resource<List<CommentsDTO>>>()
    val commentsData: LiveData<Resource<List<CommentsDTO>>> get() = _commentsData

    private val _favoritesData = MutableLiveData<Resource<List<FavoritesDTO>>>()
    val favoritesData: LiveData<Resource<List<FavoritesDTO>>> get() = _favoritesData

    var productDTO: MutableLiveData<ProductDTO> = MutableLiveData()

    init {
        getAllFavorites()
    }

    private val _basketData = MutableLiveData<Resource<BasketDTO>>()
    val basketData : LiveData<Resource<BasketDTO>> get() = _basketData


    fun getBasketUser(userId: Int){
        viewModelScope.launch{
            val response = apiRepo.getBasketUser(userId)
            response.collectLatest {
                _basketData.postValue(it)
            }
        }
    }

    fun getProductsById(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiRepo.getProductById(productId)
            response.collectLatest {
                _productData.postValue(it)
            }
        }
    }

    fun getProductComments(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiRepo.getProductComments(id)
            response.collectLatest {
                _commentsData.postValue(it)
            }
        }
    }

    fun insertFavorite(favoritesDTO: FavoritesDTO) {
        viewModelScope.launch {
            roomRepo.insertFavorite(favoritesDTO)
        }
    }

    fun getAllFavorites() {
        viewModelScope.launch {
            val data = roomRepo.getAllFavorites()
            data.collectLatest {
                _favoritesData.postValue(it)
            }
        }
    }
}