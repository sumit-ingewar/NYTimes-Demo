package com.sumit.core.domain.rxcallback

interface ResponseCallback<T> {

    fun onResponseSuccess(response: T)

    fun onResponseError(error: String)

    fun onNewtorkError(error: String)

    fun onServerError(error: String)
}