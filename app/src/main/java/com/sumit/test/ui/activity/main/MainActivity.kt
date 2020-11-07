package com.sumit.test.ui.activity.main

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.sumit.core.util.PreferenceManager
import com.sumit.test.BR
import com.sumit.test.R
import com.sumit.test.base.BaseActivity
import com.sumit.test.databinding.ActivityMainBinding
import java.lang.IllegalStateException
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(),
    MainActivityNavigator {

    // region VARIABLES
    override val viewModel = MainActivityViewModel::class.java
    override fun getBindingVariable() = BR.activityViewModel
    override val layoutId = R.layout.activity_main

    @Inject
    lateinit var preferenceManager: PreferenceManager

    // endregion

    // region LIFECYCLE
    override fun initUserInterface() {
        setSupportActionBar(viewDataBinding.toolbar)
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
