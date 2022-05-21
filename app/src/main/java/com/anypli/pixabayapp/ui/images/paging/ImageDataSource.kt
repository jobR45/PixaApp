package com.anypli.pixabayapp.ui.images.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.anypli.pixabayapp.data.model.Hits
import com.anypli.pixabayapp.data.repository.HitsRepository
import com.anypli.pixabayapp.global.listener.SchedulerProvider
import com.anypli.pixabayapp.global.utils.DebugLog
import com.anypli.pixabayapp.global.utils.FIRST_PAGE
import com.anypli.pixabayapp.global.utils.PAGE_SIZE
import com.anypli.pixabayapp.global.utils.TAG
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.withContext

class ImageDataSource(
    private val hitsRepository: HitsRepository,
    private val query: String,

) : RxPagingSource<Int, Hits>() {




    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Hits>> {
        val position = params.key ?: FIRST_PAGE

        return hitsRepository.getAllImages(position, PAGE_SIZE,query)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it.hits,position) }
            .onErrorReturn { LoadResult.Error(it) }


    }

    private fun toLoadResult(data: List<Hits>, position: Int): LoadResult<Int, Hits> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == FIRST_PAGE) null else position.minus(1),
            nextKey = if(data.isEmpty()) null else position.plus(1)
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Hits>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}