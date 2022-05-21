package com.anypli.pixabayapp.global.listener

import kotlinx.coroutines.CoroutineDispatcher

interface SchedulerProvider {
    fun dispatchersUI(): CoroutineDispatcher

    fun dispatchersDefault(): CoroutineDispatcher

    fun dispatchersIO(): CoroutineDispatcher

    fun dispatchersUnconfined(): CoroutineDispatcher
}