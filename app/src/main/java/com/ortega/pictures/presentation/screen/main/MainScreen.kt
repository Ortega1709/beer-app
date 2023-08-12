package com.ortega.pictures.presentation.screen.main

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ortega.pictures.domain.model.Photos


@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {

    val photos: LazyPagingItems<Photos> = viewModel.photosPagingFlow.collectAsLazyPagingItems()
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

    Scaffold { paddingValues ->

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            if (photos.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {

                Text(text = photos.itemSnapshotList.toString())
                
            }

        }

    }

}