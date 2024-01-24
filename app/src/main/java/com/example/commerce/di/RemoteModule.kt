package com.example.commerce.di

import com.example.commerce.common.util.BASE_URL
import com.example.commerce.data.repository.ApiRepoImpl
import com.example.commerce.data.service.remote.Api
import com.example.commerce.data.source.ApiSource
import com.example.commerce.data.source.ApiSourceImpl
import com.example.commerce.data.token.SessionManager
import com.example.commerce.domain.repo.ApiRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideApi() = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(Api::class.java)

    @Singleton
    @Provides
    fun provideApiSource(api: Api, sp: SessionManager) = ApiSourceImpl(api, sp) as ApiSource

    @Singleton
    @Provides
    fun provideApiRepo(apiSource: ApiSource) = ApiRepoImpl(apiSource) as ApiRepo
}