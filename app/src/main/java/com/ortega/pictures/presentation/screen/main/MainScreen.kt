package com.ortega.pictures.presentation.screen.main

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ortega.pictures.domain.model.Beer
import com.ortega.pictures.presentation.components.BeerItem


@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {

    val beers: LazyPagingItems<Beer> = viewModel.beerPaging.collectAsLazyPagingItems()
    val context = LocalContext.current

    LaunchedEffect(key1 = beers.loadState) {
        if(beers.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error ${(beers.loadState.refresh as LoadState.Error).error.message}",
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

            if (beers.loadState.refresh is LoadState.Loading) {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {

                    items(beers.itemCount) {
                        if(beers[it] != null) {
                            BeerItem(beers[it]!!)
                        }
                    }

                    item {
                        if (beers.loadState.append is LoadState.Loading) {
                            Column (
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(25.dp),
                                )

                            }

                        }
                    }

                }

            }

        }

    }

}