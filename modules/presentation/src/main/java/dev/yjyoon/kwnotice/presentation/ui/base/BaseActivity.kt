package dev.yjyoon.kwnotice.presentation.ui.base

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yjyoon.kwnotice.presentation.ui.theme.KWNoticeDarkColors
import dev.yjyoon.kwnotice.presentation.ui.theme.KWNoticeLightColors
import dev.yjyoon.kwnotice.presentation.ui.theme.KWNoticeTheme

abstract class BaseActivity : ComponentActivity() {

    protected fun setScreen(
        isMain: Boolean = false,
        screen: @Composable () -> Unit
    ) =
        setContent {
            val systemUiController: SystemUiController = rememberSystemUiController()
            val isDarkMode = isSystemInDarkTheme()
            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = if (isDarkMode) {
                        KWNoticeDarkColors.background
                    } else {
                        KWNoticeLightColors.background
                    },
                    darkIcons = !isDarkMode
                )
                if (isMain) {
                    systemUiController.setNavigationBarColor(
                        color = if (isDarkMode) {
                            KWNoticeDarkColors.secondaryContainer
                        } else {
                            KWNoticeLightColors.secondaryContainer
                        },
                        darkIcons = !isDarkMode
                    )
                }
            }

            KWNoticeTheme(content = screen)
        }
}
