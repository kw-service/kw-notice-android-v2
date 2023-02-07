package dev.yjyoon.kwnotice.presentation.ui.splash

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.base.BaseActivity
import dev.yjyoon.kwnotice.presentation.ui.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setScreen { SplashScreen() }

        lifecycleScope.launch {
            delay(SPLASH_TIME_MILLIS)
            viewModel.state.collect { handleState(it) }
        }
    }

    private fun handleState(state: SplashState) {
        when (state) {
            SplashState.Done -> requestNotificationPermission()
            SplashState.Waiting -> {}
        }
    }

    private fun requestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                Toast.makeText(
                    this,
                    R.string.on_disable_notification,
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            startMainActivity()
        }
    }

    private val notificationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            if (!it) onDenyNotificationPermission()
            startMainActivity()
        }

    private fun onDenyNotificationPermission() =
        Toast.makeText(
            this,
            R.string.on_deny_notification_permission,
            Toast.LENGTH_LONG
        ).show()


    private fun startMainActivity() {
        MainActivity.startActivity(this)
        finish()
    }

    companion object {
        private const val SPLASH_TIME_MILLIS = 1_500L
    }
}
