package com.sumit.demo

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import com.sumit.test.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

@SuppressLint("Registered")
class DemoApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

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

    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}