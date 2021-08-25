package com.sumit.test.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {


    val articleUrl = MutableLiveData<String>()

    fun articleUrl(url: String) {
        articleUrl.value = url
    }
}