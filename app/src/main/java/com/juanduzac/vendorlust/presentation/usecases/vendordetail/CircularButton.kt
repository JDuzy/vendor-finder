package com.juanduzac.vendorlust.presentation.usecases.vendordetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.juanduzac.vendorlust.presentation.ui.theme.Gray
import com.juanduzac.vendorlust.presentation.ui.theme.LightGray
import com.juanduzac.vendorlust.presentation.ui.theme.Shapes

@Composable
fun CircularButton(
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
            backgroundColor = Color.White,
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
