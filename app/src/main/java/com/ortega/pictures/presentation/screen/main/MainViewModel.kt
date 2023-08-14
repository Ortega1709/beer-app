package com.ortega.pictures.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.ortega.pictures.domain.entity.BeerEntity
import com.ortega.pictures.domain.mappers.toBeers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(pager: Pager<Int, BeerEntity>) : ViewModel() {

    val beerPaging =
        pager.flow.map { paging -> paging.map { it.toBeers() } }.cachedIn(viewModelScope)



}