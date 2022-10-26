package com.juanduzac.vendorlust.presentation.usecases.vendordetail

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.juanduzac.vendorlust.domain.model.Vendor
import com.juanduzac.vendorlust.presentation.ui.theme.AppbarCollapsedHeight
import com.juanduzac.vendorlust.presentation.ui.theme.AppbarExpandedHeight
import com.juanduzac.vendorlust.presentation.ui.theme.Gray
import com.juanduzac.vendorlust.presentation.ui.theme.Pink
import com.juanduzac.vendorlust.presentation.ui.theme.VendorLustTheme
import com.juanduzac.vendorlust.presentation.ui.theme.neutral800
import com.juanduzac.vendorlust.presentation.util.vendorExample
import kotlin.math.max
import kotlin.math.min

private const val HttpsString = "https://www."

@Composable
fun VendorDetailScreen(
    navController: NavController,
    vendor: Vendor,
    startCallIntent: (String) -> Unit,
    startWebIntent: (String) -> Unit,
    startEmailIntent: (String) -> Unit

) {
    val scrollState = rememberLazyListState()
    var isFaved by remember { mutableStateOf(false) }

    Box {
        Content(vendor, scrollState, startCallIntent, startWebIntent, startEmailIntent)
        ParallaxToolbar(vendor, scrollState, isFaved, navController) { isFaved = !isFaved }
    }
}

@Composable
private fun Content(
    vendor: Vendor,
    scrollState: LazyListState,
    startCallIntent: (String) -> Unit,
    startWebIntent: (String) -> Unit,
    startEmailIntent: (String) -> Unit
) {
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
                    BasicInfo(vendor, startCallIntent, startWebIntent, startEmailIntent)
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
                    .graphicsLayer { alpha = 1f - offsetProgression }
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(vendor.heroImage?.url)
                        .crossfade(true)
                        .build(),
                    placeholder = null,
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
private fun BasicInfo(
    vendor: Vendor,
    startCallIntent: (String) -> Unit,
    startWebIntent: (String) -> Unit,
    startEmailIntent: (String) -> Unit
) {
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

                ExpandableInfoRow(
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
                ) {
                    startCallIntent(number)
                }
            }

            emailAddress?.let { email ->
                InfoRow(
                    imageVector = Icons.Outlined.Email,
                    text = email,
                    tint = Pink
                ) {
                    startEmailIntent(email)
                }
            }

            websiteUrl?.let { url ->
                InfoRow(
                    imageVector = Icons.Outlined.Info,
                    text = url.removePrefix(HttpsString),
                    tint = Pink,
                    dividerBelow = false
                ) {
                    startWebIntent(url)
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 380, heightDp = 1400)
@Composable
private fun PreviewScreen() {
    VendorLustTheme {
        VendorDetailScreen(
            rememberNavController(),
            vendorExample,
            {},
            {},
            {}
        )
    }
}
