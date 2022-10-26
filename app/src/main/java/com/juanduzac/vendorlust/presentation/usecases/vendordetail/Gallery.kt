package com.juanduzac.vendorlust.presentation.usecases.vendordetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.juanduzac.vendorlust.domain.model.GalleryItem
import com.juanduzac.vendorlust.presentation.ui.theme.Shapes

@Composable
fun Gallery(galleryItems: List<GalleryItem>) {
    LazyRow(
        modifier = Modifier.padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(galleryItems) { item ->
            Spacer(modifier = Modifier.width(12.dp))

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.image?.url)
                    .crossfade(true)
                    .build(),
                placeholder = null,
                contentDescription = item.image?.alternativeText,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(Shapes.small)
                    .size(116.dp)
            )

        }
    }
}