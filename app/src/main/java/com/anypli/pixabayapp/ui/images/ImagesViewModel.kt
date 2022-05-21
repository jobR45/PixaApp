package com.anypli.pixabayapp.ui.images

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.anypli.pixabayapp.base.BaseAndroidViewModel
import com.anypli.pixabayapp.data.model.Hits
import com.anypli.pixabayapp.data.repository.HitsRepository
import com.anypli.pixabayapp.global.listener.SchedulerProvider
import com.anypli.pixabayapp.global.utils.PAGE_SIZE
import com.anypli.pixabayapp.ui.images.paging.ImageDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@HiltViewModel
class ImagesViewModel @Inject constructor(
    application: Application,
    private val schedulerProvider: SchedulerProvider,
    private val hitsRepository: HitsRepository
) :BaseAndroidViewModel(application){


    val criteria = MutableLiveData<String>()



    fun searchImages(): Flow<PagingData<Hits>>{
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { ImageDataSource(hitsRepository, "") }
        ).flow.flowOn(schedulerProvider.dispatchersIO()).cachedIn(viewModelScope)
    }

    fun searchImagesByCriteria(criteria:String): Flow<PagingData<Hits>>{
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { ImageDataSource(hitsRepository, criteria) }
        ).flow.flowOn(schedulerProvider.dispatchersIO()).cachedIn(viewModelScope)
    }

}