package com.example.commerce.data.source

import com.example.commerce.common.util.Resource
import com.example.commerce.data.dto.AddressDTO
import com.example.commerce.data.dto.AuthToken
import com.example.commerce.data.dto.BasketDTO
import com.example.commerce.data.dto.CategoriesDTO
import com.example.commerce.data.dto.CommentsDTO
import com.example.commerce.data.dto.OrderDTO
import com.example.commerce.data.dto.PaymentDTO
import com.example.commerce.data.dto.ProductDTO
import com.example.commerce.data.dto.UserDTO
import com.example.commerce.data.service.remote.Api
import com.example.commerce.data.token.SessionManager
import com.example.commerce.domain.model.AddressModel
import com.example.commerce.domain.model.BasketPostModel
import com.example.commerce.domain.model.BasketPutModel
import com.example.commerce.domain.model.LoginModel
import com.example.commerce.domain.model.OrderModel
import com.example.commerce.domain.model.PaymentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiSourceImpl @Inject constructor(private val api: Api, private val sp: SessionManager) :
    ApiSource {

    override suspend fun getAllProducts(): Resource<List<ProductDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.getAllProducts()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get all products"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun getAllCategories(): Resource<List<CategoriesDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.getAllCategories()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get all categories"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun getProductsByCategory(categoryId: Int): Resource<List<ProductDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.getProductsByCategory(categoryId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get product by category"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun getProductById(productId: Int): Resource<ProductDTO> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.getProductById(productId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get product by id"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun getAllUsers(): Resource<List<UserDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllUsers()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get all users"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun login(loginModel: LoginModel): Resource<AuthToken> {
        return try {
            Resource.loading(null)
            val response = api.login(loginModel)
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.success(it)
                } ?: Resource.error("null", null)
            } else {
                val message = "error login"
                Resource.error(message, null)
            }
        } catch (e: Exception) {
            Resource.error(e.localizedMessage ?: "catch", null)
        }
    }

    override suspend fun getProductComments(productId: Int): Resource<List<CommentsDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.getProductComments(productId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get product commentsˆ"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun getOrdersUser(userId: Int): Resource<List<OrderDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.getOrdersUser(userId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get user orders"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun putBasket(
        userId: Int,
        basketPutModel: BasketPutModel
    ): Resource<BasketDTO> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.putBasket(userId, basketPutModel)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error put basket"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun getBasketUser(userId: Int): Resource<BasketDTO> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.getBasketUser(userId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get basket user orders"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun postBasketUser(
        basketPostModel: BasketPostModel
    ): Resource<BasketDTO> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.postBasketUser(basketPostModel)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error post basket user orders"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun deleteBasket(userId: Int) {
        withContext(Dispatchers.IO){
            api.deleteBasket(userId)
        }
    }

    override suspend fun getAddressUser(userId: Int): Resource<List<AddressDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.getAddressUser(userId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get user address"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun addAddress(addressModel: AddressModel): Resource<AddressDTO> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.addAddress(addressModel)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error add address"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun getPaymentUser(userId: Int): Resource<List<PaymentDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.getPaymentUser(userId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get payment"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun addPaymentUser(paymentModel: PaymentModel): Resource<PaymentDTO> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.addPaymentUser(paymentModel)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error add payment"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun addOrder(orderModel: OrderModel): Resource<OrderDTO> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.addOrder(orderModel)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error add order"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }
}