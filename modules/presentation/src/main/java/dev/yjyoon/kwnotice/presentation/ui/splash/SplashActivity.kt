package dev.yjyoon.kwnotice.presentation.ui.splash

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.yjyoon.kwnotice.presentation.ui.base.BaseActivity
import dev.yjyoon.kwnotice.presentation.ui.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setScreen { SplashScreen() }

        lifecycleScope.launch {
            delay(SPLASH_TIME_MILLIS)
            startMainActivity()
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
