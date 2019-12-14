package com.sumit.test.di.modules

import com.sumit.core.data.remote.ArticleDataRepository
import com.sumit.core.data.remote.ArticleRepository
import com.sumit.core.di.scopes.PerApplication
import com.sumit.core.domain.executor.PostExecutionThread
import com.sumit.core.domain.remote.GsonProvider
import com.sumit.test.helper.UiThread
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DataModule {

    @Module
    companion object {
        @PerApplication
        @Provides
        @JvmStatic
        fun provideGson(): GsonProvider = GsonProvider()
    }

    @PerApplication
    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @PerApplication
    @Binds
    abstract fun bindArticleDataRepository(articleDataRepository: ArticleDataRepository): ArticleRepository
}