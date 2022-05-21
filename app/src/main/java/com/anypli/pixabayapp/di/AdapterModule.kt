package com.anypli.pixabayapp.di

import com.anypli.pixabayapp.ui.images.paging.ImagePaginationAdapter
import com.anypli.pixabayapp.ui.images.paging.LoaderStateAdapter
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(ActivityComponent::class)
object AdapterModule {


    @Provides
    fun providesImageAdapter(requestManager: RequestManager): ImagePaginationAdapter{
        return ImagePaginationAdapter(requestManager)
    }

    @Provides
    fun providesFooterAdapter() : LoaderStateAdapter{
        return LoaderStateAdapter()
    }
}