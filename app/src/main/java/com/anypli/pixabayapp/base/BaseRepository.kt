package com.anypli.pixabayapp.base

import androidx.room.Database
import com.anypli.pixabayapp.data.database.DataBase
import com.anypli.pixabayapp.data.retrofit.ApiClient

abstract class BaseRepository(
    protected val apiClient: ApiClient,
    protected val database: DataBase
)