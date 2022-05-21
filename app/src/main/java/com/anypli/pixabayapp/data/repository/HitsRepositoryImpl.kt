package com.anypli.pixabayapp.data.repository

import com.anypli.pixabayapp.base.BaseRepository
import com.anypli.pixabayapp.data.database.DataBase
import com.anypli.pixabayapp.data.model.Hits
import com.anypli.pixabayapp.data.model.HitsResponse
import com.anypli.pixabayapp.data.retrofit.ApiClient
import com.anypli.pixabayapp.global.utils.DebugLog
import com.anypli.pixabayapp.global.utils.TAG
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HitsRepositoryImpl @Inject constructor(
    apiClient: ApiClient,
    dataBase: DataBase
) :BaseRepository(apiClient,dataBase), HitsRepository{


    override  fun getAllImages(page: Int, pageSize: Int, query: String): Single<HitsResponse> {

        //update database

      return apiClient.getAllImages(page,pageSize,query)

    }


}