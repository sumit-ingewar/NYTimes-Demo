package com.sumit.test.di

import com.sumit.core.domain.remote.GsonProvider
import com.sumit.core.domain.remote.NetworkUtil
import com.sumit.test.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideNetworkService(
        gsonProvider: GsonProvider
    ) = NetworkUtil(
        gsonProvider = gsonProvider,
        endPoint = BuildConfig.BASE_URL
    )
}