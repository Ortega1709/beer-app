package com.ortega.pictures.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ortega.pictures.domain.entity.PhotosEntity

@Database(
    entities = [PhotosEntity::class],
    version = 1
)
abstract class PhotosDB: RoomDatabase() {

    abstract fun photosDAO(): PhotosDAO

}