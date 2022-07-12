package dev.yjyoon.kwnotice.presentation.ui.base

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeDarkColors
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeLightColors
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme

abstract class BaseActivity : ComponentActivity() {

    protected fun setScreen(screen: @Composable () -> Unit) =
        setContent {
            val systemUiController: SystemUiController = rememberSystemUiController()
            val isDarkMode = isSystemInDarkTheme()
            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = if (isDarkMode) {
                        KwNoticeDarkColors.background
                    } else {
                        KwNoticeLightColors.background
                    },
                    darkIcons = !isDarkMode
                )
            }

            KwNoticeTheme(content = screen)
        }
}
