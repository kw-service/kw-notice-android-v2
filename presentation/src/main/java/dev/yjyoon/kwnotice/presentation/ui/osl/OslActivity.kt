package dev.yjyoon.kwnotice.presentation.ui.osl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.yjyoon.kwnotice.presentation.ui.base.BaseActivity
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme

@AndroidEntryPoint
class OslActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KwNoticeTheme {
                OslScreen(
                    onClose = ::finish
                )
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, OslActivity::class.java)
            context.startActivity(intent)
        }
    }
}
