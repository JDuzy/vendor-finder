package com.juanduzac.vendorlust.presentation.usecases.vendordetail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.juanduzac.vendorlust.presentation.ui.theme.Gray

@Composable
fun ExpandableInfoRow(
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
                )
        ),
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
