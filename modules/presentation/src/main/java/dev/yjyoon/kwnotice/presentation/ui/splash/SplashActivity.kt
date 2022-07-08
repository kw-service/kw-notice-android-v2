package dev.yjyoon.kwnotice.presentation.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.yjyoon.kwnotice.presentation.ui.base.BaseActivity
import dev.yjyoon.kwnotice.presentation.ui.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setScreen { SplashScreen(viewModel = viewModel) }

        lifecycleScope.launch {
            delay(SPLASH_TIME_MILLIS)
            viewModel.state.collect { handleState(it) }
        }
    }

    private fun handleState(state: SplashState) =
        when (state) {
            SplashState.Done -> startMainActivity()
            SplashState.Waiting -> Unit
        }

    private fun startMainActivity() {
        MainActivity.startActivity(this)
        finish()
    }

    companion object {
        private const val SPLASH_TIME_MILLIS = 1_500L
    }
}
