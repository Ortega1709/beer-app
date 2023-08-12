package com.ortega.pictures.data.datasource.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.MediatorResult
import androidx.room.withTransaction
import com.ortega.pictures.data.datasource.local.PhotosDAO
import com.ortega.pictures.data.datasource.local.PhotosDB
import com.ortega.pictures.data.repository.PhotosRepository
import com.ortega.pictures.domain.entity.PhotosEntity
import com.ortega.pictures.domain.mappers.toPhotosEntity
import retrofit2.HttpException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PhotosRemoteMediator @Inject constructor(
    private val photosDB: PhotosDB,
    private val photosRepository: PhotosRepository
) : RemoteMediator<Int, PhotosEntity>() {
    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, PhotosEntity>
    ): MediatorResult {

        return try {

            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lItem = state.lastItemOrNull()
                    if (lItem == null) {
                        1
                    } else {
                        (lItem.id / state.config.pageSize) + 10
                    }
                }
            }

            val response = photosRepository.getAllPhotos(
                page = loadKey,
                perPage = state.config.pageSize
            )

            photosDB.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    photosDB.photosDAO().clearAll()
                }

                val photosEntity = response.photos.map { it.toPhotosEntity() }
                photosDB.photosDAO().upsertAll(photosEntity)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.photos.isEmpty()
            )

        } catch (e: Exception) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }

    }


}