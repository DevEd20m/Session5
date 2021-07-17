package com.faztbit.restapilesson5.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface ScopeTwo : CoroutineScope {
    var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun initScope() {
        job = SupervisorJob()
    }

    fun destroyScope() {
        job.cancel()
    }

    class Imp() : ScopeTwo {
        override lateinit var job: Job
    }
}