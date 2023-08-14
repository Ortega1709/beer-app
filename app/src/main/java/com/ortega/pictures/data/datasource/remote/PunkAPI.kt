package com.ortega.pictures.data.datasource.remote

import com.ortega.pictures.domain.model.Beers
import com.ortega.pictures.util.Constants.ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query

interface PunkAPI {

    @GET(ENDPOINT)
    suspend fun getAllBeers(@Query("page") page: Int, @Query("per_page") perPage: Int): List<Beers>


}