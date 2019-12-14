package com.sumit.core.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun schedular(): Scheduler
}