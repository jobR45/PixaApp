package com.anypli.pixabayapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DisposableModule {

    @Provides
    fun providesDisposable() : CompositeDisposable{
        return CompositeDisposable()
    }
}