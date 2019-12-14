package com.sumit.core.domain

import com.google.gson.Gson
import com.sumit.core.DemoLogger
import com.sumit.core.domain.executor.PostExecutionThread
import com.sumit.core.domain.executor.ThreadExecutor
import com.sumit.core.domain.remote.*
import com.sumit.core.domain.rxcallback.CallbackWrapper
import com.sumit.core.safeGet
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.TimeoutException

abstract class SingleUseCase<T : BaseResponse, Params> {

    val TAG = SingleUseCase::class.java.simpleName

    private val threadScheduler: Scheduler

    private val postExecutionThread: PostExecutionThread

    private var useCaseListener: UseCaseListener? = null

    constructor(postExecutionThread: PostExecutionThread) {
        this.postExecutionThread = postExecutionThread
        threadScheduler = Schedulers.io()
    }

    constructor(
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ) {
        this.postExecutionThread = postExecutionThread
        threadScheduler = Schedulers.io()
    }

    abstract fun buildUseCase(params: Params?): Single<T>

    fun setListener(useCaseListener: UseCaseListener): SingleUseCase<T, Params> {
        this.useCaseListener = useCaseListener
        return this
    }

    fun execute(
        callbackWrapper: CallbackWrapper<T>?,
        params: Params? = null
    ): Disposable? {
        if (callbackWrapper == null) {
            return null
        }

        val single = buildUseCase(params)
            .subscribeOn(threadScheduler)
            .observeOn(postExecutionThread.schedular())

        useCaseListener?.onPreExecute()

        return single.subscribe({ result ->
            useCaseListener?.onPostExecute()

            callbackWrapper.onResponseSuccess(result)

        }, { exception ->
            useCaseListener?.onPostExecute()

            when (exception) {

                is HttpException -> {
                    DemoLogger.e("HttpException", exception.code().toString())
                    exception.response().errorBody()?.let {
                        val error = it.string().safeGet()
                        DemoLogger.e("Retrofit Exception", error)
                        handleResponseError(error = error, callbackWrapper = callbackWrapper)
                    }
                }

                is ServerNotAvailableException -> {
                    callbackWrapper.onServerError("Server not available")
                }

                is HTTPNotFoundException -> {
                    callbackWrapper.onResponseError("No data found")
                }

                is IOException,
                is TimeoutException -> {
                    callbackWrapper.onNewtorkError(exception.localizedMessage)
                }

                is HTTPBadRequest -> {
                    callbackWrapper.onResponseError("SOMETHING WENT WRONG")
                }

            }
        })
    }

    private fun handleResponseError(
        error: String,
        callbackWrapper: CallbackWrapper<T>?
    ) {
        try {
            val baseError = Gson().fromJson(
                error,
                BaseError::class.java
            )

            if (baseError != null) {
                baseError?.let {
                    if (it.error.isEmpty()) {
                        it.error.let { error ->
                            callbackWrapper?.onResponseError(error = error)

                        }
                    } else {
                        callbackWrapper?.onResponseError(error = error)
                    }
                }
            } else {
                callbackWrapper?.onResponseError("SOMETHING WENT WRONG")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            callbackWrapper?.onResponseError("RESPONSE ERROR")
        }
    }


}