package com.ortega.pictures.domain.mappers

import com.ortega.pictures.domain.entity.BeerEntity
import com.ortega.pictures.domain.model.Beer

// to BeersEntity
fun Beer.toBeersEntity(): BeerEntity {
    return BeerEntity(id, name, tagline, description, imageUrl)
}

// to Beers
fun BeerEntity.toBeers(): Beer {
    return Beer(id, name, tagline, description, imageUrl)
}