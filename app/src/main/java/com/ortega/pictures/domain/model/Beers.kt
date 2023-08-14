package com.ortega.pictures.domain.model

import com.google.gson.Gson

data class Beers(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String?
)