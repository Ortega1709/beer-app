package com.ortega.pictures.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ortega.pictures.domain.entity.BeerEntity

@Database(entities = [BeerEntity::class], version = 1)
abstract class BeerDB: RoomDatabase() {

    abstract fun beersDAO(): BeerDAO

}