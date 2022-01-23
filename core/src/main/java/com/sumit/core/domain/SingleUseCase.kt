package com.sumit.core.domain

import com.google.gson.Gson
import com.sumit.core.DemoLogger
import com.sumit.core.domain.executor.PostExecutionThread
import com.sumit.core.domain.remote.*
import com.sumit.core.domain.rxcallback.CallbackWrapper
import com.sumit.core.extensions.TAG
import com.sumit.core.extensions.empty
import com.sumit.core.extensions.safeGet
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.util.concurrent.TimeoutException

abstract class SingleUseCase<T : BaseResponse, Params>(private val postExecutionThread: PostExecutionThread) {

    companion object {
        private const val SOMETHING_WENT_WRONG = "Something went wrong , please try again later"
    }

    private val threadScheduler: Scheduler = Schedulers.io()

    abstract fun buildUseCase(params: Params?): Single<T>

    fun execute(
        callbackWrapper: CallbackWrapper<T>?,
        params: Params? = null
    ): Disposable? {

        if (callbackWrapper == null) {
            return null
        }

        val single = buildUseCase(params)
            .subscribeOn(threadScheduler)
            .observeOn(postExecutionThread.scheduler())

        return single.subscribe({ result ->
            callbackWrapper.onApiSuccess(result)
        }, { exception ->

            val baseError: BaseError
            when (exception) {
                is HttpException -> {
                    exception.response().errorBody().run {
                        val error = this?.string().safeGet()
                        DemoLogger.e(
                            TAG,
                            "Retrofit exception $error with error code ${exception.code()}"
                        )

                        when (exception.response().code()) {
                            // 429 - Too many request code
                            429 -> callbackWrapper.onApiError(
                                BaseError(
                                    errorMessage = getErrorMessageFromResponse(error),
                                    errorCode = ResponseErrors.HTTP_TOO_MANY_REQUEST,
                                    errorBody = error
                                )
                            )
                            else -> handleResponseError(error, callbackWrapper)
                        }
                    }
                }

                is ServerNotAvailableException -> {
                    baseError = BaseError(
                        errorMessage = "Server not available",
                        errorCode = ResponseErrors.HTTP_UNAVAILABLE
                    )
                    callbackWrapper.onApiError(baseError)
                }

                is HTTPNotFoundException -> {
                    baseError = BaseError(
                        errorMessage = "Server not available",
                        errorCode = ResponseErrors.HTTP_NOT_FOUND
                    )
                    callbackWrapper.onApiError(baseError)
                }

                is ConnectException -> {
                    callbackWrapper.onApiError(
                        BaseError(
                            errorMessage = "Internet not available",
                            errorCode = ResponseErrors.CONNECTIVITY_EXCEPTION
                        )
                    )
                }

                is IOException,
                is TimeoutException -> {
                    baseError = if (exception.localizedMessage != null) {
                        BaseError(
                            errorMessage = exception.localizedMessage!!,
                            errorCode = ResponseErrors.UNKNOWN_EXCEPTION
                        )
                    } else {
                        BaseError(
                            errorMessage = SOMETHING_WENT_WRONG,
                            errorCode = ResponseErrors.UNKNOWN_EXCEPTION
                        )
                    }
                    callbackWrapper.onApiError(baseError)
                }

                is HTTPBadRequest -> {
                    callbackWrapper.onApiError(
                        BaseError(
                            errorMessage = SOMETHING_WENT_WRONG,
                            errorCode = ResponseErrors.HTTP_BAD_REQUEST
                        )
                    )
                }
            }
        })

    }

    private fun getErrorMessageFromResponse(error: String): String {
        var errorMessage: String = String.empty
        try {
            val baseError = Gson().fromJson(
                error,
                BaseError::class.java
            )

            if (baseError != null) {
                baseError.apply {
                    errorMessage = if (this.message.isNotBlank()) {
                        this.message
                    } else {
                        this.errorMessage
                    }
                }
            } else {
                errorMessage = SOMETHING_WENT_WRONG
            }

        } catch (e: Exception) {
            errorMessage = SOMETHING_WENT_WRONG
        } finally {
            return errorMessage
        }
    }

    private fun handleResponseError(
        error: String,
        callbackWrapper: CallbackWrapper<T>?
    ) {
        callbackWrapper?.onApiError(
            error = BaseError(
                errorMessage = getErrorMessageFromResponse(error),
                errorCode = ResponseErrors.RESPONSE_ERROR
            )
        )
    }



}