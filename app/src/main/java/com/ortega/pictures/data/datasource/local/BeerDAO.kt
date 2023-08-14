package com.ortega.pictures.data.datasource.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ortega.pictures.domain.entity.BeerEntity

@Dao
interface BeerDAO {

    @Upsert
    suspend fun upsertAll(beerEntities: List<BeerEntity>)

    @Query("SELECT * FROM beer")
    suspend fun pagingSource(): PagingSource<Int, BeerEntity>

    @Query("DELETE FROM beer")
    suspend fun clearAll()

}