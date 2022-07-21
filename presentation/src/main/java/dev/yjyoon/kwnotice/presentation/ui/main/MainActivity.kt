package dev.yjyoon.kwnotice.presentation.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import dev.yjyoon.kwnotice.domain.model.VersionName
import dev.yjyoon.kwnotice.presentation.ui.base.BaseActivity
import dev.yjyoon.kwnotice.presentation.ui.webview.WebViewActivity
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var versionName: VersionName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setScreen {
            MainScreen(
                onClickNotice = ::startWebViewActivity,
                versionName = versionName
            )
        }
    }

    private fun startWebViewActivity(url: String) {
        val intent = WebViewActivity.getIntent(this, url)
        startActivity(intent)
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
