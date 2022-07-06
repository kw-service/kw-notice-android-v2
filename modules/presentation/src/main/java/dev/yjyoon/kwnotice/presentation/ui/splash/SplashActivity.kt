package dev.yjyoon.kwnotice.presentation.ui.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.yjyoon.kwnotice.presentation.ui.main.MainActivity
import dev.yjyoon.kwnotice.presentation.ui.theme.KWNoticeDarkColors
import dev.yjyoon.kwnotice.presentation.ui.theme.KWNoticeLightColors
import dev.yjyoon.kwnotice.presentation.ui.theme.KWNoticeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent()

        lifecycleScope.launch {
            delay(SPLASH_TIME_MILLIS)
            startMainActivity()
        }
    }

    private fun setContent() = setContent {
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
        }

        KWNoticeTheme {
            SplashScreen()
        }
    }

    private fun startMainActivity() {
        MainActivity.startActivity(this)
        finish()
    }

    companion object {
        private const val SPLASH_TIME_MILLIS = 1_500L
    }
}
