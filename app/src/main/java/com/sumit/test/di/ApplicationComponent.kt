package com.sumit.test.di

import com.sumit.core.di.scopes.PerApplication
import com.sumit.demo.DemoApp
import com.sumit.test.di.modules.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        DataModule::class,
        RemoteModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<DemoApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DemoApp>()
}