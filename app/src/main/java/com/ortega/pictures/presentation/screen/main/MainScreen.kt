package com.ortega.pictures.presentation.screen.main

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems


@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {

    val photos = viewModel.photosPagingFlow.collectAsLazyPagingItems()
    val context = LocalContext.current

    LaunchedEffect(key1 = photos.loadState) {
        if(photos.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error ${(photos.loadState.refresh as LoadState.Error).error.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}