package com.anypli.pixabayapp.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object GlideModule {

    @Provides
    fun providesGlideAdapter(context : Context)  : RequestManager {
        return Glide.with(context)

    }
}