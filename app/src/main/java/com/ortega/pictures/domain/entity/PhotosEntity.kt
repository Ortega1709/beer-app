package com.ortega.pictures.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "photos"
)
data class PhotosEntity (

    @PrimaryKey
    val id: Int,
    val url: String,
    val title: String,
    val description: String

)