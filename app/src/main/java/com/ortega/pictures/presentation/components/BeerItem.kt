package com.ortega.pictures.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ortega.pictures.domain.model.Beer
import com.ortega.pictures.presentation.theme.PicturesTheme

@Composable
fun BeerItem(beer: Beer) {

    Column {
        ListItem(
            leadingContent = {
                AsyncImage(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White),
                    model = beer.imageUrl,
                    onLoading = {  },
                    contentDescription = null
                )
            },
            overlineContent = { Text(text = beer.tagline) },
            headlineContent = { Text(text = beer.name) },
            supportingContent = { Text(text = beer.description) }
        )

        Divider(
            thickness = .5.dp
        )
    }

}


@Preview
@Composable
fun BeerItemPreview() {
    PicturesTheme {
        val beer = Beer(
            1,
            "Primus",
            "Birds are amazing",
            "Quick shoot photos of birds",
            imageUrl = "https://www.google.com"
        )

        BeerItem(beer)
    }
}