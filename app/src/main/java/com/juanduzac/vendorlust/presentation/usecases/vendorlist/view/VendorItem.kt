package com.juanduzac.vendorlust.presentation.usecases.vendorlist.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.juanduzac.vendorlust.domain.model.Image
import com.juanduzac.vendorlust.domain.model.Vendor
import com.juanduzac.vendorlust.presentation.ui.theme.LightGray
import com.juanduzac.vendorlust.presentation.ui.theme.Pink

const val VendorItemTestTag = "VendorItemTestTag"

@Composable
fun VendorItem(
    vendor: Vendor,
    isOpen: Boolean,
    onClick: (Vendor) -> Unit,
    modifier: Modifier = Modifier,
    bottomDivider: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(vendor) }
            .testTag(VendorItemTestTag),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(vendor.heroImage?.url)
                    .crossfade(true)
                    .build(),
                placeholder = null,
                contentDescription = vendor.heroImage?.alternativeText,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .size(52.dp)
                    .border(1.dp, LightGray, RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                modifier = Modifier.weight(2f),
                text = vendor.name ?: "",
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onBackground,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Spacer(modifier = Modifier.width(8.dp))

            OpenOrClosedView(Modifier.weight(1f), isOpen)
        }
    }
    if (bottomDivider)
        Divider(thickness = 1.dp, color =  LightGray)
}

@Composable
private fun OpenOrClosedView(modifier: Modifier = Modifier, isOpen: Boolean) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = if (isOpen) "Open" else "Closed", style = MaterialTheme.typography.caption)
        Spacer(modifier = Modifier.width(4.dp))
        Icon(if (isOpen) Icons.Rounded.Check else Icons.Rounded.Close, contentDescription = null, tint = Pink)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOpenOrClosedView() {
    Column(Modifier.fillMaxWidth()) {
        OpenOrClosedView(isOpen = true)
        Spacer(modifier = Modifier.height(8.dp))
        OpenOrClosedView(isOpen = false)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVendorItem() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        VendorItem(
            vendor = Vendor(
                name = "Telefonia Claro",
                heroImage = Image(
                    url =
                    "https://axp-cms-nobu-stack-strapiapps3bucket-172yzo4e7x7hw" +
                        ".s3.eu-west-1.amazonaws.com/small_NHMB_Faena_2_c8f1ecc1ed.jpg"
                )
            ),
            true,
            {}
        )
        Spacer(modifier = Modifier.height(8.dp))
        VendorItem(
            vendor = Vendor(
                name = "Tarjeta de credito mastercard",
                heroImage = Image(
                    url =
                    "https://axp-cms-nobu-stack-strapiapps3bucket-172yzo4e7x7hw.s3" +
                        ".eu-west-1.amazonaws.com/small_NHMB_Faena_2_c8f1ecc1ed.jpg"
                )
            ),
            false,
            {}
        )
    }
}
