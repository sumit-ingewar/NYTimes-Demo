package com.sumit.test

import android.annotation.SuppressLint
import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.sumit.core.util.PreferenceManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp

@SuppressLint("Registered")
class DemoApp : Application() {

    @Inject
    lateinit var preferenceManager: PreferenceManager

    companion object {
        lateinit var instance: DemoApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this


        if (preferenceManager.darkThemeEnabled())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }
}