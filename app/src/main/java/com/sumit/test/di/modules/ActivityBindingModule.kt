package com.sumit.test.di.modules

import com.sumit.core.di.scopes.PerActivity
import com.sumit.test.ui.activity.main.MainActivity
import com.sumit.test.ui.activity.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity() : MainActivity
}