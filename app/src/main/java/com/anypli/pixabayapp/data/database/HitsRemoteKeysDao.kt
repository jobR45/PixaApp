package com.anypli.pixabayapp.data.database

import androidx.paging.PagingSource
import androidx.room.*
import com.anypli.pixabayapp.data.model.Hits
import com.anypli.pixabayapp.data.model.HitsRemoteKeys

@Dao
interface HitsRemoteKeysDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( remoteKey: List<HitsRemoteKeys>)

    @Query("SELECT * FROM hits_remote_keys WHERE hitId = :hitId")
    fun remoteKeysByHitId(hitId:Int): HitsRemoteKeys?


    @Query("DELETE FROM hits_remote_keys")
     fun deleteAll()

}