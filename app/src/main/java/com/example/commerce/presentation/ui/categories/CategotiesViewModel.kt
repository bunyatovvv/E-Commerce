package com.example.commerce.presentation.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerce.common.util.Resource
import com.example.commerce.data.dto.CategoriesDTO
import com.example.commerce.data.repository.ApiRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategotiesViewModel @Inject constructor(private val repo: ApiRepoImpl) : ViewModel() {

    private val _categoryData=MutableLiveData<Resource<List<CategoriesDTO>>>()
    val categoryData: LiveData<Resource<List<CategoriesDTO>>> get()=_categoryData

    init {
        getAllCategories()
    }

    fun getAllCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.getAllCategories()
            response.collectLatest {
                _categoryData.postValue(it)
            }
        }
    }
}