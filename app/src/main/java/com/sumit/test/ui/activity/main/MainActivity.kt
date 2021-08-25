package com.sumit.test.ui.activity.main

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import com.sumit.core.util.PreferenceManager
import com.sumit.test.R
import com.sumit.test.base.BaseActivity
import com.sumit.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(),
    MainActivityNavigator {

    // region VARIABLES

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    @Inject
    lateinit var preferenceManager: PreferenceManager

    // endregion

    // region LIFECYCLE

    override fun initUserInterface() {
        setSupportActionBar(viewBinding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_theme, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuTheme -> {

                preferenceManager.enableDarkTheme(preferenceManager.darkThemeEnabled().not())

                if (preferenceManager.darkThemeEnabled())
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                true
            }
            else -> throw IllegalStateException("Can not handle this menu")
        }
    }

    // endregion
}
