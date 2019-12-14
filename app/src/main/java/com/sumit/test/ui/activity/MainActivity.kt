package com.sumit.test.ui.activity

import com.sumit.test.BR
import com.sumit.test.R
import com.sumit.test.base.BaseActivity
import com.sumit.test.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(),
    MainActivityNavigator {
    override val viewModel: Class<MainActivityViewModel>
        get() = MainActivityViewModel::class.java

    override fun getBindingVariable() = BR._all

    override val layoutId = R.layout.activity_main

    override fun initUserInterface() {
    }

}
