package dev.yjyoon.kwnotice.presentation.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.yjyoon.kwnotice.presentation.ui.theme.KWNoticeDarkColors
import dev.yjyoon.kwnotice.presentation.ui.theme.KWNoticeLightColors
import dev.yjyoon.kwnotice.presentation.ui.theme.KWNoticeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent()
    }

    private fun setContent() = setContent {
        val systemUiController: SystemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(
            if (isSystemInDarkTheme()) {
                KWNoticeDarkColors.background
            } else {
                KWNoticeLightColors.background
            }
        )

        KWNoticeTheme {
            MainScreen()
        }
    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
