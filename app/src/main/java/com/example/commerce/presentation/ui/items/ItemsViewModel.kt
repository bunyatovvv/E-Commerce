package com.example.commerce.presentation.ui.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerce.common.util.Resource
import com.example.commerce.data.dto.ProductDTO
import com.example.commerce.data.repository.ApiRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(var repo: ApiRepoImpl) : ViewModel() {

    private val _productData=MutableLiveData<Resource<List<ProductDTO>>>()
    val productData: LiveData<Resource<List<ProductDTO>>> get()=_productData

    fun getProductsByCategory(categoryId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getProductsByCategory(categoryId).collectLatest {
                _productData.postValue(it)
            }
        }
    }
}