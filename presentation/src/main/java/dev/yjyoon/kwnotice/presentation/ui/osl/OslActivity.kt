package dev.yjyoon.kwnotice.presentation.ui.osl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import dev.yjyoon.kwnotice.presentation.ui.base.BaseActivity

@AndroidEntryPoint
class OslActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setScreen { OslScreen(onClose = ::finish) }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, OslActivity::class.java)
            context.startActivity(intent)
        }
    }
}
