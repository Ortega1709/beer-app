package com.ortega.pictures.domain.model

data class Output(
    val limit: String,
    val offset: String,
    val success: String,
    val message: String,
    val photos: List<Photos>
)