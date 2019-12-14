package com.sumit.test.di.modules

import com.sumit.core.data.remote.ArticleService
import com.sumit.core.di.scopes.PerApplication
import com.sumit.core.domain.remote.NetworkUtil
import dagger.Module
import dagger.Provides

@Module
class RemoteModule {

    @Module
    companion object {

        @PerApplication
        @Provides
        @JvmStatic
        fun provideArticleService(networkUtil: NetworkUtil) =
            networkUtil.create(ArticleService::class.java)

    }
}