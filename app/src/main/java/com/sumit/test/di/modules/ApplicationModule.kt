package com.sumit.test.di.modules

import android.content.Context
import com.sumit.core.di.qualifier.ApplicationContext
import com.sumit.core.di.scopes.PerApplication
import com.sumit.demo.DemoApp
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @ApplicationContext
    @PerApplication
    @Binds
    abstract fun bindApplication(application: DemoApp) : Context
}