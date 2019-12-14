package com.sumit.test.helper

import com.sumit.core.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class UiThread @Inject constructor() : PostExecutionThread {
    override fun schedular(): Scheduler = AndroidSchedulers.mainThread()
}