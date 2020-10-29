package com.sumit.core.util

import com.sumit.core.domain.remote.BaseError
import com.sumit.core.domain.remote.ResponseErrors

object ApiErrorMessages {

    // TODO - Get the error messages from resources
    fun getErrorMessage(
        error: BaseError
    ): String {
        return when (error.errorCode) {
            ResponseErrors.RESPONSE_ERROR -> error.errorMessage
            ResponseErrors.CONNECTIVITY_EXCEPTION -> "Internet connection not available"
            ResponseErrors.HTTP_NOT_FOUND,
            ResponseErrors.HTTP_UNAVAILABLE -> "Can't reach server at the moment"
            ResponseErrors.HTTP_BAD_REQUEST -> "Bad request"
            ResponseErrors.UNKNOWN_EXCEPTION -> "Something went wrong , please try again later"
            else -> "Something went wrong , please try again later"
        }
    }
}