package com.ortega.pictures.data.datasource.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ortega.pictures.domain.entity.PhotosEntity
import com.ortega.pictures.domain.model.Photos

@Dao
interface PhotosDAO {

    @Upsert
    suspend fun upsertAll(photos: List<Photos>)

    @Query("SELECT * FROM photos")
    fun pagingSource(): PagingSource<Int, PhotosEntity>

    @Query("DELETE FROM photos")
    suspend fun clearAll()

}