package com.sumit.demo

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.sumit.core.util.PreferenceManager
import com.sumit.test.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

@SuppressLint("Registered")
class DemoApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var preferenceManager: PreferenceManager

    companion object {
        lateinit var instance: DemoApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        DaggerApplicationComponent
            .builder()
            .create(this)
            .inject(this)

        if (preferenceManager.darkThemeEnabled())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}