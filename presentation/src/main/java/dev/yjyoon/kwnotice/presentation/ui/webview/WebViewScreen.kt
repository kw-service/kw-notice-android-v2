package dev.yjyoon.kwnotice.presentation.ui.webview

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.web.LoadingState
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import dev.yjyoon.kwnotice.presentation.R

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(url: String) {
    val state = rememberWebViewState(url = url)
    val navigator = rememberWebViewNavigator()
    val loadingState = state.loadingState
    val uriHandler = LocalUriHandler.current

    Scaffold(
        floatingActionButton = {
            WebViewFloatingActionButton(
                onClick = { uriHandler.openUri(url) }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (loadingState is LoadingState.Loading) {
                LinearProgressIndicator(
                    progress = loadingState.progress,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            WebView(
                state = state,
                modifier = Modifier.weight(1f),
                navigator = navigator,
                onCreated = {
                    it.settings.javaScriptEnabled = true
                    it.settings.builtInZoomControls = true
                    it.settings.setSupportZoom(true)
                }
            )
        }
    }
}

@Composable
fun WebViewFloatingActionButton(
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        text = {
            Text(
                text = stringResource(id = R.string.open_in_browser)
            )
        },
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_browser),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
        },
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    )
}
