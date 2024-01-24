package com.example.commerce.data.repository

import com.example.commerce.common.util.Resource
import com.example.commerce.common.util.Status
import com.example.commerce.data.dto.AddressDTO
import com.example.commerce.data.dto.AuthToken
import com.example.commerce.data.dto.BasketDTO
import com.example.commerce.data.dto.CategoriesDTO
import com.example.commerce.data.dto.CommentsDTO
import com.example.commerce.data.dto.OrderDTO
import com.example.commerce.data.dto.PaymentDTO
import com.example.commerce.data.dto.ProductDTO
import com.example.commerce.data.dto.UserDTO
import com.example.commerce.data.source.ApiSource
import com.example.commerce.domain.model.AddressModel
import com.example.commerce.domain.model.BasketModel
import com.example.commerce.domain.model.BasketPostModel
import com.example.commerce.domain.model.BasketPutModel
import com.example.commerce.domain.model.LoginModel
import com.example.commerce.domain.model.OrderModel
import com.example.commerce.domain.model.PaymentModel
import com.example.commerce.domain.repo.ApiRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepoImpl @Inject constructor(private val source: ApiSource) : ApiRepo {

    override suspend fun getAllProducts(): Flow<Resource<List<ProductDTO>>> = flow {
        emit(Resource.loading(null))
        val response = source.getAllProducts()
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(emptyList()))
            }
        }
    }

    override suspend fun getProductById(productId: Int): Flow<Resource<ProductDTO>> = flow {
        emit(Resource.loading(null))
        val response = source.getProductById(productId)
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(null))
            }
        }
    }

    override suspend fun getAllUsers(): Flow<Resource<List<UserDTO>>> = flow {
        emit(Resource.loading(null))
        val response = source.getAllUsers()
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(null))
            }
        }
    }

    override suspend fun login(loginModel: LoginModel): Resource<AuthToken> {
        val response = source.login(loginModel)
        return when (response.status) {
            Status.SUCCESS -> {
                Resource.success(response.data)
            }

            Status.ERROR -> {
                Resource.error(response.message ?: "Error source", null)
            }

            else -> {
                Resource.loading(null)
            }
        }
    }

    override suspend fun getProductComments(productId: Int): Flow<Resource<List<CommentsDTO>>> =
        flow {
            emit(Resource.loading(null))
            val response = source.getProductComments(productId)
            when (response.status) {
                Status.SUCCESS -> {
                    emit(Resource.success(response.data))
                }

                Status.ERROR -> {
                    emit(Resource.error(response.message ?: "Error", null))
                }

                else -> {
                    emit(Resource.loading(emptyList()))
                }
            }
        }

    override suspend fun getOrdersUser(userId: Int): Flow<Resource<List<OrderDTO>>> = flow {
        emit(Resource.loading(null))
        val response = source.getOrdersUser(userId)
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(emptyList()))
            }
        }
    }

    override suspend fun putBasket(
        userId: Int,
        basketPutModel: BasketPutModel
    ): Flow<Resource<BasketDTO>> = flow {
        emit(Resource.loading(null))
        val response = source.putBasket(userId, basketPutModel)
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(null))
            }
        }
    }

    override suspend fun getBasketUser(userId: Int): Flow<Resource<BasketDTO>> = flow {
        emit(Resource.loading(null))
        val response = source.getBasketUser(userId)
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(null))
            }
        }
    }

    override suspend fun postBasketUser(
        basketPostModel: BasketPostModel
    ): Flow<Resource<BasketDTO>> = flow {
        emit(Resource.loading(null))
        val response = source.postBasketUser(basketPostModel)
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(null))
            }
        }
    }

    override suspend fun deleteBasket(userId: Int) {
        source.deleteBasket(userId)
    }

    override suspend fun getAddressUser(userId: Int): Flow<Resource<List<AddressDTO>>> = flow {
        emit(Resource.loading(null))
        val response = source.getAddressUser(userId)
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(null))
            }
        }
    }

    override suspend fun getAllCategories(): Flow<Resource<List<CategoriesDTO>>> = flow {
        emit(Resource.loading(null))
        val response = source.getAllCategories()
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(emptyList()))
            }
        }
    }

    override suspend fun getProductsByCategory(categoryId: Int): Flow<Resource<List<ProductDTO>>> =
        flow {
            emit(Resource.loading(null))
            val response = source.getProductsByCategory(categoryId)
            when (response.status) {
                Status.SUCCESS -> {
                    emit(Resource.success(response.data))
                }

                Status.ERROR -> {
                    emit(Resource.error(response.message ?: "Error", null))
                }

                else -> {
                    emit(Resource.loading(null))
                }
            }
        }

    override suspend fun addAddress(addressModel: AddressModel): Flow<Resource<AddressDTO>> =
        flow {
            emit(Resource.loading(null))
            val response = source.addAddress(addressModel)
            when (response.status) {
                Status.SUCCESS -> {
                    emit(Resource.success(response.data))
                }

                Status.ERROR -> {
                    emit(Resource.error(response.message ?: "Error", null))
                }

                else -> {
                    emit(Resource.loading(null))
                }
            }
        }

    override suspend fun getPaymentUser(userId: Int): Flow<Resource<List<PaymentDTO>>> = flow {
        emit(Resource.loading(null))
        val response = source.getPaymentUser(userId)
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(null))
            }
        }
    }

    override suspend fun addPaymentUser(paymentModel: PaymentModel): Flow<Resource<PaymentDTO>> = flow {
        emit(Resource.loading(null))
        val response = source.addPaymentUser(paymentModel)
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(null))
            }
        }
    }

    override suspend fun addOrder(orderModel: OrderModel): Flow<Resource<OrderDTO>> = flow {
        emit(Resource.loading(null))
        val response = source.addOrder(orderModel)
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(null))
            }
        }
    }
}