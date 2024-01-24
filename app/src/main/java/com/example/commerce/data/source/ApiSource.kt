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
import com.example.commerce.domain.model.AddressModel
import com.example.commerce.domain.model.BasketPostModel
import com.example.commerce.domain.model.BasketPutModel
import com.example.commerce.domain.model.LoginModel
import com.example.commerce.domain.model.OrderModel
import com.example.commerce.domain.model.PaymentModel

interface ApiSource {

    suspend fun getAllProducts(): Resource<List<ProductDTO>>

    suspend fun getAllCategories(): Resource<List<CategoriesDTO>>

    suspend fun getProductsByCategory(categoryId: Int): Resource<List<ProductDTO>>

    suspend fun getProductById(productId: Int): Resource<ProductDTO>

    suspend fun getAllUsers(): Resource<List<UserDTO>>

    suspend fun login(loginModel: LoginModel): Resource<AuthToken>

    suspend fun getProductComments(productId: Int): Resource<List<CommentsDTO>>

    suspend fun getOrdersUser(userId: Int): Resource<List<OrderDTO>>

    suspend fun putBasket(userId: Int, basketPutModel: BasketPutModel): Resource<BasketDTO>

    suspend fun getBasketUser(userId: Int): Resource<BasketDTO>

    suspend fun postBasketUser(basketPostModel: BasketPostModel): Resource<BasketDTO>

    suspend fun deleteBasket(userId:Int)

    suspend fun getAddressUser(userId: Int): Resource<List<AddressDTO>>

    suspend fun addAddress(addressModel: AddressModel) : Resource<AddressDTO>

    suspend fun getPaymentUser(userId: Int) : Resource<List<PaymentDTO>>

    suspend fun addPaymentUser(paymentModel: PaymentModel) : Resource<PaymentDTO>

    suspend fun addOrder(orderModel: OrderModel) : Resource<OrderDTO>





}