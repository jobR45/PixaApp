package com.anypli.pixabayapp.data.database

import androidx.paging.PagingSource
import androidx.room.*
import com.anypli.pixabayapp.data.model.Hits

@Dao
interface HitsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll( hits: List<Hits>)

    @Query("SELECT * FROM hits ORDER BY id ASC")
    fun selectAll(): PagingSource<Int, Hits>

    @Delete
     fun delete(hit: Hits)

    @Query("DELETE from hits")
     fun deleteAll()

}