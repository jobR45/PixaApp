package com.anypli.pixabayapp.data.repository

import androidx.annotation.WorkerThread
import com.anypli.pixabayapp.data.model.Hits
import com.anypli.pixabayapp.data.model.HitsResponse
import io.reactivex.Single

interface HitsRepository {

    @WorkerThread
    fun getAllImages(page: Int, pageSize: Int,query:String) : Single<HitsResponse>

}