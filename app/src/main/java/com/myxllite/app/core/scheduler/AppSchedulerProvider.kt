package com.myxllite.app.core.scheduler

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
class AppSchedulerProvider : SchedulerProvider {

    override fun computation(): CoroutineContext = Dispatchers.Default

    override fun io(): CoroutineContext = Dispatchers.IO

    override fun ui(): CoroutineContext = Dispatchers.Main
}
