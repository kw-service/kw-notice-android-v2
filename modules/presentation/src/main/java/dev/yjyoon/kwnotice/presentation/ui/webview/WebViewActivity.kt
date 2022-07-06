package dev.yjyoon.kwnotice.presentation.ui.webview

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import dev.yjyoon.kwnotice.presentation.ui.base.BaseActivity

@AndroidEntryPoint
class WebViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url: String = intent.getStringExtra("url")!!

        setScreen { WebViewScreen(url = url) }
    }
}