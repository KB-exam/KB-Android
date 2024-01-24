package com.kb.cbt.model.service.module

import com.kb.cbt.model.service.AccountService
import com.kb.cbt.model.service.ApiService
import com.kb.cbt.model.service.StorageService
import com.kb.cbt.model.service.impl.AccountServiceImpl
//import com.kb.cbt.model.service.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

//    @Binds
//    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService

//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://port-0-kb-backend-f9ohr2alrqzna2e.sel5.cloudtype.app/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit): ApiService {
//        return retrofit.create(ApiService::class.java)
//    }
}