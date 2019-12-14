package com.sumit.test.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {


    val articleUrl = MutableLiveData<String>()

    fun articleUrl(url: String) {
        articleUrl.value = url
    }
}