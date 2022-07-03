package dev.yjyoon.kwnotice.presentation.ui.webview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.yjyoon.kwnotice.presentation.ui.theme.KWNoticeTheme

@AndroidEntryPoint
class WebViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url: String = intent.getStringExtra("url")!!

        setContent {
            KWNoticeTheme {
                WebViewScreen(url = url)
            }
        }
    }
}