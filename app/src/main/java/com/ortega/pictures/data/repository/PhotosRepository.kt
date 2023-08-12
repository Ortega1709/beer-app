package com.ortega.pictures.data.repository

import com.ortega.pictures.data.datasource.remote.PhotosAPI
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val photosAPI: PhotosAPI
) {

    suspend fun getAllPhotos(page: Int, perPage: Int) = photosAPI.getAllPhotos(page, perPage)

}