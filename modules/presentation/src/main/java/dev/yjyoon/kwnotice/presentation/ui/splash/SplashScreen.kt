package dev.yjyoon.kwnotice.presentation.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.theme.KWNoticeDarkColors
import dev.yjyoon.kwnotice.presentation.ui.theme.KWNoticeLightColors

@Composable
fun SplashScreen() {
    val isDarkMode = isSystemInDarkTheme()
    val logoTint = if (isDarkMode) {
        KWNoticeDarkColors.primary
    } else {
        KWNoticeLightColors.primary
    }
    val backgroundColor = if (isDarkMode) {
        KWNoticeDarkColors.background
    } else {
        KWNoticeLightColors.background
    }

    Box(
        Modifier
            .background(backgroundColor)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            tint = logoTint,
            modifier = Modifier.size(64.dp)
        )
    }
}
