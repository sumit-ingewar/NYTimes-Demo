package com.sumit.test.di

import com.sumit.core.data.remote.ArticleService
import com.sumit.core.domain.remote.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideArticleService(networkUtil: NetworkUtil) =
        networkUtil.create(ArticleService::class.java)
}
