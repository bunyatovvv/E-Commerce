package com.example.commerce.data.service.remote

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
import com.example.commerce.domain.model.RegisterModel
import com.example.commerce.domain.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface Api {

    @POST("auth/login")
    suspend fun login(@Body loginModel: LoginModel): Response<AuthToken>

    @POST("auth/signup")
    suspend fun register(@Body registerModel: RegisterModel): Response<AuthToken>

    @GET("auth/me")
    suspend fun getCurrentUser(@Header("Authorization") token: String): Response<UserModel>

    @GET("products")
    suspend fun getAllProducts(): Response<List<ProductDTO>>

    @GET("categories")
    suspend fun getAllCategories(): Response<List<CategoriesDTO>>

    @GET("products/category/{category_id}")
    suspend fun getProductsByCategory(@Path("category_id") categoryId: Int): Response<List<ProductDTO>>

    @GET("products/{product_id}")
    suspend fun getProductById(@Path("product_id") productId: Int): Response<ProductDTO>

    @GET("users")
    suspend fun getAllUsers(): Response<List<UserDTO>>

    @GET("comments/productId/{product_id}")
    suspend fun getProductComments(@Path("product_id") productId: Int): Response<List<CommentsDTO>>

    @GET("orders/user/{user_id}")
    suspend fun getOrdersUser(@Path("user_id") userId: Int): Response<List<OrderDTO>>

    @PUT("baskets/{users_id}")
    suspend fun putBasket(@Path("users_id") userId: Int, @Body basketPutModel: BasketPutModel): Response<BasketDTO>

    @GET("baskets/{users_id}")
    suspend fun getBasketUser(@Path("users_id") userId: Int): Response<BasketDTO>

    @POST("baskets")
    suspend fun postBasketUser(@Body basketPostModel: BasketPostModel): Response<BasketDTO>

    @DELETE("baskets/{users_id}")
    suspend fun deleteBasket(@Path("users_id") userId: Int)

    @GET("address/{users_id}")
    suspend fun getAddressUser(@Path("users_id") userId: Int): Response<List<AddressDTO>>

    @POST("address")
    suspend fun addAddress(@Body addressModel: AddressModel) : Response<AddressDTO>

    @GET("payment/{users_id}")
    suspend fun getPaymentUser(@Path("users_id") userId : Int) : Response<List<PaymentDTO>>

    @POST("payment")
    suspend fun addPaymentUser(@Body paymentModel: PaymentModel) : Response<PaymentDTO>

    @POST("orders")
    suspend fun addOrder(@Body orderModel: OrderModel) : Response<OrderDTO>


}