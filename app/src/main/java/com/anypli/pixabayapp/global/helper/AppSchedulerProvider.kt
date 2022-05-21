package com.anypli.pixabayapp.global.helper

import com.anypli.pixabayapp.global.listener.SchedulerProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AppSchedulerProvider : SchedulerProvider {

    override fun dispatchersUI(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    override fun dispatchersDefault(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    override fun dispatchersIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    override fun dispatchersUnconfined(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }
}