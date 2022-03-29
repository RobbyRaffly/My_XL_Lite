package com.myxllite.app.core.scheduler

import kotlin.coroutines.CoroutineContext

interface SchedulerProvider {

    fun computation(): CoroutineContext

    fun io(): CoroutineContext

    fun ui(): CoroutineContext
}
