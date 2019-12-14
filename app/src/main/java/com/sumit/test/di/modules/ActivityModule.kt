package com.sumit.test.di.modules

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import com.sumit.core.di.qualifier.ActivityContext
import com.sumit.core.di.scopes.PerActivity
import com.sumit.test.base.BaseActivity
import com.sumit.test.base.BaseViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerActivity
        fun provideFragmentManager(activity: AppCompatActivity): FragmentManager {
            return activity.supportFragmentManager
        }
    }

    @Binds
    @PerActivity
    abstract fun bindAppCompatActivity(activity: BaseActivity<ViewDataBinding, BaseViewModel<*>>): AppCompatActivity

    @Binds
    @PerActivity
    abstract fun bindActivity(activity: AppCompatActivity): Activity

    @ActivityContext
    @Binds
    @PerActivity
    abstract fun bindActivityContext(activity: Activity): Context
}