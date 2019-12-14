package com.sumit.test.ui.fragment

interface ListFragmentNavigator {

    fun setAdapter(list: ArrayList<Any>)

    fun clearItems()

    fun diplayErrorToast(error: String)
}