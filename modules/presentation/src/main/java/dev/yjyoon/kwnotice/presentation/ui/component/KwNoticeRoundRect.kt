package dev.yjyoon.kwnotice.presentation.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp


@Composable
fun KwNoticeRoundRect(
    modifier: Modifier = Modifier,
    radius: Dp,
    width: Dp = Dp.Unspecified,
    height: Dp = Dp.Unspecified,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Canvas(
        modifier = modifier.then(
            when {
                width != Dp.Unspecified -> Modifier
                    .fillMaxHeight()
                    .width(width)
                height != Dp.Unspecified -> Modifier
                    .fillMaxWidth()
                    .height(width)
                else -> Modifier.size(width, height)
            }
        )
    ) {
        drawRoundRect(
            color = color,
            cornerRadius = CornerRadius(radius.toPx(), radius.toPx())
        )
    }
}
