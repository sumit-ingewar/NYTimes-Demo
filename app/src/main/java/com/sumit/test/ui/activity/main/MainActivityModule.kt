package com.sumit.test.ui.activity.main

import com.sumit.core.di.scopes.PerActivity
import com.sumit.core.di.scopes.PerFragment
import com.sumit.test.di.modules.ActivityModule
import com.sumit.test.ui.fragment.ListFragment
import com.sumit.test.ui.fragment.details.DetailsFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ActivityModule::class])
abstract class MainActivityModule {

    @PerActivity
    @Binds
    abstract fun bindMainActivity(mainActivity: MainActivity): MainActivity

    @PerFragment
    @ContributesAndroidInjector
    abstract fun provideListFragment(): ListFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun provideDetailsFragment(): DetailsFragment
}