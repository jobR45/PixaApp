package com.anypli.pixabayapp.di

import android.content.Context
import androidx.room.Database
import com.anypli.pixabayapp.data.database.DataBase
import com.anypli.pixabayapp.data.database.DataBaseBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun providesDataBase(context : Context): DataBase {
        return DataBaseBuilder.getRepoDataBase(context)
    }
}