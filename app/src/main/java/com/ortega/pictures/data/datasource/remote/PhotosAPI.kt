package com.ortega.pictures.data.datasource.remote

import com.ortega.pictures.domain.model.Output
import com.ortega.pictures.util.Constants.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosAPI {

    @GET(ENDPOINT)
    suspend fun getAllPhotos(
        @Query("offset") page: Int,
        @Query("limit") perPage: Int
    ): Response<Output>



}