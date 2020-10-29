package com.sumit.core.domain.rxcallback

import com.sumit.core.domain.remote.BaseError

interface ResponseCallback<T> {
    fun onApiSuccess(response: T)
    fun onApiError(error: BaseError)
}