package com.sumit.test.ui.activity.main

import com.sumit.test.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : BaseViewModel<MainActivityNavigator>() {
}