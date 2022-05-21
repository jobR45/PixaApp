package com.anypli.pixabayapp.di

import com.anypli.pixabayapp.data.repository.HitsRepository
import com.anypli.pixabayapp.data.repository.HitsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesHitsRepository(hitsRepositoryImpl: HitsRepositoryImpl) :HitsRepository
}