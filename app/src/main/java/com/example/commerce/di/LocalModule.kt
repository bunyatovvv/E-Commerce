package com.example.commerce.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.commerce.data.repository.RoomRepoImpl
import com.example.commerce.data.service.local.RoomDAO
import com.example.commerce.data.service.local.RoomDB
import com.example.commerce.data.source.RoomSource
import com.example.commerce.data.source.RoomSourceImpl
import com.example.commerce.data.token.SessionManager
import com.example.commerce.data.token.SessionManager.Companion.USER_TOKEN
import com.example.commerce.domain.repo.RoomRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun injectSp(
        @ApplicationContext context: Context
    ) = context.getSharedPreferences(USER_TOKEN, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun injectAppSP(sp: SharedPreferences) = SessionManager(sp)

    @Singleton
    @Provides
    fun injectRoomDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        RoomDB::class.java, "FavoritesDB"
    ).build()

    @Singleton
    @Provides
    fun injectRoomDAO(roomDB: RoomDB) = roomDB.getDao()

    @Singleton
    @Provides
    fun injectRepo(roomSource: RoomSource) = RoomRepoImpl(roomSource) as RoomRepo

    @Singleton
    @Provides
    fun injectSource(roomDAO: RoomDAO) = RoomSourceImpl(roomDAO) as RoomSource
}