package com.ortega.pictures.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.map
import com.ortega.pictures.data.datasource.remote.PhotosRemoteMediator
import com.ortega.pictures.domain.entity.PhotosEntity
import com.ortega.pictures.domain.mappers.toPhotos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor( pager: Pager<Int, PhotosEntity>): ViewModel() {


    val photosPagingFlow = pager.flow.map { pagingData ->
        pagingData.map {
            it.toPhotos()
        }
    }




}