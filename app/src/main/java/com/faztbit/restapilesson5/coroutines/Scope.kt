package com.deved.weatherapp.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface Scope : CoroutineScope {
    var job: Job
    val dispatcher: CoroutineDispatcher

    override val coroutineContext: CoroutineContext
        get() = dispatcher + job

    fun initScope() {
        job = SupervisorJob()
    }

    fun destroyScope() {
        job.cancel()
    }

    class Impl(override val dispatcher: CoroutineDispatcher) : Scope {
        override lateinit var job: Job
    }

}