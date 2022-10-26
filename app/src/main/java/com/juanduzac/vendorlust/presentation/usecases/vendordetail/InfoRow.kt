package com.juanduzac.vendorlust.presentation.usecases.vendordetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.juanduzac.vendorlust.presentation.ui.theme.Gray

@Composable
fun InfoRow(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    text: String,
    tint: Color,
    dividerBelow: Boolean = true,
    trailingIcon: ImageVector? = null,
    trailingTint: Color = Gray,
    trailingRotation: Float = 0f,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = onClick?.let { modifier.then(Modifier.clickable { it() }) } ?: modifier,
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
                trailingIcon, null, tint = trailingTint,
                modifier = Modifier
                    .height(32.dp)
                    .rotate(trailingRotation)
            )
        }
    }
    if (dividerBelow)
        Divider(thickness = 1.dp, color = Gray)
}
