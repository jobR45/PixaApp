package com.anypli.pixabayapp.di

import com.anypli.pixabayapp.global.helper.AppSchedulerProvider
import com.anypli.pixabayapp.global.listener.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SchedulerModule {

    @Provides
    @Singleton
    fun providesDispatchers() : SchedulerProvider {
        return AppSchedulerProvider()
    }
}