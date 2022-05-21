package com.anypli.pixabayapp.di


import com.anypli.pixabayapp.BuildConfig
import com.anypli.pixabayapp.data.retrofit.ApiClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiClientModule {


    @Provides
    @Singleton
    fun providesApiClient(retrofit: Retrofit) : ApiClient{

        return retrofit.create(ApiClient::class.java)
    }


    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient,moshi: Moshi): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }



    @Provides
    @Singleton
    fun provideMoshi() : Moshi {
        return  Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    }
}