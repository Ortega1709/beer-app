package com.ortega.pictures.data.datasource.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.ortega.pictures.data.datasource.local.BeerDB
import com.ortega.pictures.data.repository.BeerRepository
import com.ortega.pictures.domain.entity.BeerEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator @Inject constructor(
    private val beerDB: BeerDB,
    private val beerRepository: BeerRepository
): RemoteMediator<Int, BeerEntity>() {
    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, BeerEntity>): MediatorResult {



    }


}