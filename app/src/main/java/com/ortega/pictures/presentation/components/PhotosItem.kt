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
import com.ortega.pictures.presentation.theme.PicturesTheme

@Composable
fun PhotosItem(photos: Photos) {

    Column {
        ListItem(
            leadingContent = {
                AsyncImage(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Gray),
                    model = photos.url,
                    onLoading = {  },
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            },
            headlineContent = { Text(text = photos.title) },
            supportingContent = { Text(text = photos.description) }
        )

        Divider(
            thickness = .5.dp
        )
    }

}


@Preview
@Composable
fun PhotosItemPreview() {
    PicturesTheme {
        val photos = Photos(
            1,
            "https://www.google.com",
            "Birds are amazing",
            "Quick shoot photos of birds"
        )

        PhotosItem(photos)
    }
}