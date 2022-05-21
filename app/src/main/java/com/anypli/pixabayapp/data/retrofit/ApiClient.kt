package com.anypli.pixabayapp.data.retrofit


import com.anypli.pixabayapp.BuildConfig
import com.anypli.pixabayapp.data.model.HitsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {


    @GET("api/?key=${BuildConfig.API_KEY}")
     fun getAllImages(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
        @Query("q") query: String
    ) : Single<HitsResponse>
}