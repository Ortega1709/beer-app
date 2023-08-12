package com.ortega.pictures.domain.mappers

import com.ortega.pictures.domain.entity.PhotosEntity
import com.ortega.pictures.domain.model.Photos


// to Photos Entity Mapper
fun Photos.toPhotosEntity(): PhotosEntity {
    return PhotosEntity(id, url, title, description)
}


// to Photos Mapper
fun PhotosEntity.toPhotos(): Photos {
    return Photos(id, url, title, description)
}