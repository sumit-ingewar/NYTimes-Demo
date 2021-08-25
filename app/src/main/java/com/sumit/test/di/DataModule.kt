package com.sumit.test.di

import android.content.Context
import com.sumit.core.data.remote.ArticleDataRepository
import com.sumit.core.data.remote.ArticleRepository
import com.sumit.core.data.remote.ArticleUseCase
import com.sumit.core.domain.executor.PostExecutionThread
import com.sumit.core.domain.remote.GsonProvider
import com.sumit.core.util.PreferenceManager
import com.sumit.test.helper.UiThread
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideGson(): GsonProvider = GsonProvider()

    @Provides
    @Singleton
    fun provideArticleUseCase(
        articleService: ArticleRepository,
        postExecutionThread: PostExecutionThread
    ) = ArticleUseCase(articleService, postExecutionThread = postExecutionThread)

    @Provides
    @Singleton
    fun providePreferenceManager(@ApplicationContext appContext: Context) =
        PreferenceManager(appContext)

}

@InstallIn(SingletonComponent::class)
@Module
abstract class MiscModule {
    @Singleton
    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @Binds
    abstract fun bindArticleDataRepository(articleDataRepository: ArticleDataRepository): ArticleRepository
}