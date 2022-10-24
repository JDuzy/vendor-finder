package com.juanduzac.vendorlust.presentation.usecases.vendordetail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.juanduzac.vendorlust.domain.model.GalleryItem
import com.juanduzac.vendorlust.domain.model.Vendor
import com.juanduzac.vendorlust.presentation.navigation.Screen
import com.juanduzac.vendorlust.presentation.ui.theme.AppbarCollapsedHeight
import com.juanduzac.vendorlust.presentation.ui.theme.AppbarExpandedHeight
import com.juanduzac.vendorlust.presentation.ui.theme.Gray
import com.juanduzac.vendorlust.presentation.ui.theme.LightGray
import com.juanduzac.vendorlust.presentation.ui.theme.Pink
import com.juanduzac.vendorlust.presentation.ui.theme.Shapes
import com.juanduzac.vendorlust.presentation.ui.theme.VendorLustTheme
import com.juanduzac.vendorlust.presentation.ui.theme.neutral800
import com.juanduzac.vendorlust.presentation.util.vendorExample
import kotlin.math.max
import kotlin.math.min

private const val HttpsString = "https://www."

@Composable
fun VendorDetailScreen(navController: NavController, vendor: Vendor) {
    val scrollState = rememberLazyListState()
    var isFaved by remember { mutableStateOf(false) }

    Box {
        Content(vendor, scrollState)
        ParallaxToolbar(vendor, scrollState, isFaved, navController) { isFaved = !isFaved }
    }

}

@Composable
private fun Content(vendor: Vendor, scrollState: LazyListState) {
    LazyColumn(contentPadding = PaddingValues(top = AppbarExpandedHeight), state = scrollState) {
        with(vendor) {
            item {
                description?.let { descriptionText ->
                    Description(descriptionText)
                    Divider(
                        modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp),
                        thickness = 1.dp,
                        color = Gray
                    )
                }

                contactInfo?.let {
                    BasicInfo(vendor)
                }

                gallery?.let { galleryItems ->
                    Gallery(galleryItems)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
private fun ParallaxToolbar(
    vendor: Vendor,
    scrollState: LazyListState,
    isFaved: Boolean,
    navController: NavController,
    onIsFaved: () -> Unit
) {
    val imageHeight = AppbarExpandedHeight - AppbarCollapsedHeight

    val maxOffset = with(LocalDensity.current) {
        imageHeight.roundToPx()
    } - LocalWindowInsets.current.systemBars.layoutInsets.top

    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgression = max(0f, offset * 3f - 2f * maxOffset) / maxOffset

    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = White,
        modifier = Modifier
            .height(AppbarExpandedHeight)
            .offset { IntOffset(x = 0, y = -offset) },
        elevation = if (offset == maxOffset) 4.dp else 0.dp
    ) {
        Column {
            Box(
                Modifier
                    .height(imageHeight)
                    .graphicsLayer { alpha = 1f - offsetProgression }) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(vendor.heroImage?.url)
                        .crossfade(true)
                        .build(),
                    placeholder = null, // TODO ADD PLACEHOLDER
                    contentDescription = vendor.heroImage?.alternativeText,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.4f, Transparent),
                                    Pair(1f, White)
                                )
                            )
                        )
                )
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .height(AppbarCollapsedHeight),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = vendor.name ?: "",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = neutral800,
                    modifier = Modifier
                        .padding(horizontal = (16 + 32 * offsetProgression).dp)
                        .scale(1f - 0.25f * offsetProgression)
                )
            }
        }

    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(AppbarCollapsedHeight)
            .padding(horizontal = 16.dp)
    ) {
        CircularButton(Icons.Outlined.ArrowBack) { navController.popBackStack() }
        CircularButton(Icons.Outlined.Favorite, color = if (isFaved) Pink else Gray) { onIsFaved() }
    }

}

@Composable
private fun CircularButton(
    imageVector: ImageVector,
    color: Color = Gray,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(),
        shape = Shapes.small,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = White,
            contentColor = color
        ),
        elevation = elevation,
        modifier = Modifier
            .height(38.dp)
            .width(38.dp),
        border = BorderStroke(1.dp, LightGray)
    ) {
        Icon(imageVector = imageVector, contentDescription = null)
    }
}

@Composable
private fun Gallery(galleryItems: List<GalleryItem>) {
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
                placeholder = null, // TODO ADD PLACEHOLDER
                contentDescription = item.image?.alternativeText,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(Shapes.small)
                    .size(116.dp)
            )

        }
    }
}

@Composable
private fun Description(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.body1,
        color = neutral800,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun BasicInfo(vendor: Vendor) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        with(vendor.contactInfo!!) {

            address?.addressLine1?.let { addrLine1 ->
                with(address) {
                    InfoRow(
                        imageVector = Icons.Outlined.LocationOn,
                        text = "$addrLine1, $postalCode $city",
                        tint = Pink
                    )
                }
            }

            vendor.openingHours?.let { openingHoursInWeek ->
                val openingHoursForTodayText = openingHoursInWeek.getOpeningHoursTextForToday()
                val weeklyOpeningText = openingHoursInWeek.getWeeklyOpeningHoursText()

                expandableInfoRow(
                    imageVector = Icons.Outlined.DateRange,
                    text = openingHoursForTodayText,
                    tint = Pink,
                    trailingIcon = Icons.Outlined.ArrowDropDown,
                    descriptionTexts = weeklyOpeningText
                )
            }

            phoneNumber?.let { number ->
                InfoRow(
                    imageVector = Icons.Outlined.Phone,
                    text = number,
                    tint = Pink
                )
            }

            emailAddress?.let { email ->
                InfoRow(
                    imageVector = Icons.Outlined.Email,
                    text = email,
                    tint = Pink
                )
            }

            websiteUrl?.let { url ->
                InfoRow(
                    imageVector = Icons.Outlined.Info,
                    text = url.removePrefix(HttpsString),
                    tint = Pink,
                    dividerBelow = false
                )
            }

        }

    }
}

@Composable
private fun InfoRow(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    text: String,
    tint: Color,
    dividerBelow: Boolean = true,
    trailingIcon: ImageVector? = null,
    trailingTint: Color = Gray,
    trailingRotation: Float = 0f
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector, null, tint = tint, modifier = Modifier.height(32.dp))

        Spacer(Modifier.width(8.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        trailingIcon?.let {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                trailingIcon, null, tint = trailingTint, modifier = Modifier
                    .height(32.dp)
                    .rotate(trailingRotation)
            )
        }
    }
    if (dividerBelow)
        Divider(thickness = 1.dp, color = Gray)
}

@Composable
private fun expandableInfoRow(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    text: String,
    tint: Color,
    trailingIcon: ImageVector?,
    dividerBelow: Boolean = true,
    descriptionTexts: List<String>
) {
    var isExpanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f)

    InfoRow(
        modifier = modifier.then(
            Modifier
                .clickable { isExpanded = !isExpanded }
                .animateContentSize(
                    animationSpec = tween(
                        delayMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                )),
        imageVector = imageVector,
        text = text,
        tint = tint,
        trailingIcon = trailingIcon,
        trailingRotation = rotationState,
        dividerBelow = false
    )
    if (isExpanded) {
        descriptionTexts.forEach {
            Text(
                text = it,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.body2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
    if (dividerBelow)
        Divider(thickness = 1.dp, color = Gray)
}

@Preview(showBackground = true, widthDp = 380, heightDp = 1400)
@Composable
private fun PreviewScreen() {
    VendorLustTheme {
        VendorDetailScreen(
            rememberNavController(),
            vendorExample
        )
    }

}