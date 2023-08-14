package com.ortega.pictures.domain.model


data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String?
)