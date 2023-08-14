package com.ortega.pictures.data.repository

import com.ortega.pictures.data.datasource.remote.PunkAPI
import javax.inject.Inject

class BeersRepository @Inject constructor(private val punkAPI: PunkAPI) {

    suspend fun getAllBeers(page: Int, perPage: Int) = punkAPI.getAllBeers(page, perPage)

}