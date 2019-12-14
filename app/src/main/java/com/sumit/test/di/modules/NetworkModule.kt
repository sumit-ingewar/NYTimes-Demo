package com.sumit.test.di.modules

import com.sumit.core.di.scopes.PerApplication
import com.sumit.core.domain.remote.GsonProvider
import com.sumit.core.domain.remote.NetworkUtil
import com.sumit.test.BuildConfig
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    @PerApplication
    fun provideNetworkService(
        gsonProvider: GsonProvider
    ) = NetworkUtil(
        gsonProvider = gsonProvider,
        endPoint = BuildConfig.URL
    )
}