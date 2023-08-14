package com.ortega.pictures.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beers")
data class BeersEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String?
)