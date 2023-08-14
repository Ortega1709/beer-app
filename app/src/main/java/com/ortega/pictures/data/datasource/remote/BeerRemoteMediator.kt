package com.ortega.pictures.data.datasource.remote

import android.net.http.HttpException
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ortega.pictures.data.datasource.local.BeerDB
import com.ortega.pictures.data.repository.BeerRepository
import com.ortega.pictures.domain.entity.BeerEntity
import com.ortega.pictures.domain.mappers.toBeersEntity
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator @Inject constructor(
    private val beerDB: BeerDB,
    private val beerRepository: BeerRepository
): RemoteMediator<Int, BeerEntity>() {
    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, BeerEntity>): MediatorResult {

        return try {

            val key = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> {
                    val lItem = state.lastItemOrNull()
                    if (lItem == null) { 1 } else { (lItem.id / state.config.pageSize) + 1 }
                }
            }

            val beers = beerRepository.getAllBeers(page = key, perPage = state.config.pageSize)

            beerDB.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    beerDB.beersDAO().clearAll()
                }

                val beerEntities = beers.map { it.toBeersEntity() }
                beerDB.beersDAO().upsertAll(beerEntities)
            }

            MediatorResult.Success(beers.isEmpty())

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: retrofit2.HttpException) {
            MediatorResult.Error(e)
        }

    }


}