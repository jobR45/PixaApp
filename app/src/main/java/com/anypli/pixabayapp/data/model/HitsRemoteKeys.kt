package com.anypli.pixabayapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hits_remote_keys")
data class HitsRemoteKeys(
    @PrimaryKey
    val hitId: Int,
    val prevKey: Int?,
    val nextKey: Int
)
