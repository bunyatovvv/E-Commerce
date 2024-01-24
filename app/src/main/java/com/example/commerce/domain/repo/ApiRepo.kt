package com.example.commerce.domain.repo

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
import com.example.commerce.domain.model.AddressModel
import com.example.commerce.domain.model.BasketModel
import com.example.commerce.domain.model.BasketPostModel
import com.example.commerce.domain.model.BasketPutModel
import com.example.commerce.domain.model.LoginModel
import com.example.commerce.domain.model.OrderModel
import com.example.commerce.domain.model.PaymentModel
import kotlinx.coroutines.flow.Flow

interface ApiRepo {

    suspend fun getAllProducts(): Flow<Resource<List<ProductDTO>>>

    suspend fun getAllCategories(): Flow<Resource<List<CategoriesDTO>>>

    suspend fun getProductsByCategory(categoryId: Int): Flow<Resource<List<ProductDTO>>>

    suspend fun getProductById(productId: Int): Flow<Resource<ProductDTO>>

    suspend fun getAllUsers(): Flow<Resource<List<UserDTO>>>

    suspend fun login(loginModel: LoginModel): Resource<AuthToken>

    suspend fun getProductComments(productId: Int): Flow<Resource<List<CommentsDTO>>>

    suspend fun getOrdersUser(userId: Int): Flow<Resource<List<OrderDTO>>>

    suspend fun putBasket(userId: Int, basketPutModel: BasketPutModel): Flow<Resource<BasketDTO>>

    suspend fun getBasketUser(userId: Int): Flow<Resource<BasketDTO>>

    suspend fun postBasketUser(basketPostModel: BasketPostModel): Flow<Resource<BasketDTO>>

    suspend fun deleteBasket(userId:Int)

    suspend fun getAddressUser(userId: Int): Flow<Resource<List<AddressDTO>>>

    suspend fun addAddress(addressModel: AddressModel) : Flow<Resource<AddressDTO>>

    suspend fun getPaymentUser(userId: Int) : Flow<Resource<List<PaymentDTO>>>

    suspend fun addPaymentUser(paymentModel: PaymentModel) : Flow<Resource<PaymentDTO>>

    suspend fun addOrder(orderModel: OrderModel) : Flow<Resource<OrderDTO>>


}