package com.anypli.pixabayapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anypli.pixabayapp.data.model.Hits
import com.anypli.pixabayapp.data.model.HitsRemoteKeys


@Database(entities = [Hits::class,HitsRemoteKeys::class], version = 1,exportSchema = false)
abstract class DataBase :RoomDatabase() {
    abstract fun hitsDao(): HitsDao
    abstract fun remoteKeysHitsDao() : HitsRemoteKeysDao
}