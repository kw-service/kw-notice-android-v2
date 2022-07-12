package dev.yjyoon.kwnotice.presentation.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun KwNoticeDivider(
    horizontalPadding: Dp = 16.dp,
    verticalPadding: Dp = 16.dp
) {
    Divider(
        Modifier.padding(horizontal = horizontalPadding, vertical = verticalPadding),
        color = MaterialTheme.colorScheme.surfaceVariant
    )
}