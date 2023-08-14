package com.ortega.pictures.domain.mappers

import com.ortega.pictures.domain.entity.BeersEntity
import com.ortega.pictures.domain.model.Beers

// to BeersEntity
fun Beers.toBeersEntity(): BeersEntity {
    return BeersEntity(id, name, tagline, description, imageUrl)
}

// to Beers
fun BeersEntity.toBeers(): Beers {
    return Beers(id, name, tagline, description, imageUrl)
}