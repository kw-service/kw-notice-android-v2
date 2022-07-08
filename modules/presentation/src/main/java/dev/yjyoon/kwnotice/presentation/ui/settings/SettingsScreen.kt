package dev.yjyoon.kwnotice.presentation.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.domain.model.FcmTopic
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeDivider
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeLoading
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeSwitchBar
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeTopAppBar
import dev.yjyoon.kwnotice.presentation.ui.model.FcmTopicModel
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTypography

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    SettingsScreen(
        uiState = uiState,
        onSubscribe = viewModel::subscribeTo,
        onUnsubscribe = viewModel::unsubscribeFrom
    )
}

@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onSubscribe: (FcmTopic) -> Unit,
    onUnsubscribe: (FcmTopic) -> Unit
) {
    when (uiState) {
        is SettingsUiState.Success -> {
            SettingsContent(
                uiState = uiState,
                onSubscribe = onSubscribe,
                onUnsubscribe = onUnsubscribe
            )
        }
        SettingsUiState.Loading -> {
            KwNoticeLoading()
        }
        SettingsUiState.Failure -> {}
    }
}

@Composable
fun SettingsContent(
    uiState: SettingsUiState.Success,
    onSubscribe: (FcmTopic) -> Unit,
    onUnsubscribe: (FcmTopic) -> Unit
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        KwNoticeTopAppBar(
            titleText = stringResource(id = R.string.navigation_settings),
            actionIcon = Icons.Outlined.Info,
            onActionClick = {}
        )
        SettingsTitle(
            Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.kw_home)
        )
        Spacer(Modifier.height(4.dp))
        FcmTopicSwitchBar(
            uiState = uiState,
            fcmTopicModel = FcmTopicModel(FcmTopic.KwHomeNew),
            onSubscribe = onSubscribe,
            onUnsubscribe = onUnsubscribe
        )
        FcmTopicSwitchBar(
            uiState = uiState,
            fcmTopicModel = FcmTopicModel(FcmTopic.KwHomeEdit),
            onSubscribe = onSubscribe,
            onUnsubscribe = onUnsubscribe
        )
        KwNoticeDivider()
        SettingsTitle(
            Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.sw_central)
        )
        Spacer(Modifier.height(4.dp))
        FcmTopicSwitchBar(
            uiState = uiState,
            fcmTopicModel = FcmTopicModel(FcmTopic.SwCentralNew),
            onSubscribe = onSubscribe,
            onUnsubscribe = onUnsubscribe
        )
    }
}

@Composable
fun FcmTopicSwitchBar(
    uiState: SettingsUiState.Success,
    fcmTopicModel: FcmTopicModel,
    onSubscribe: (FcmTopic) -> Unit,
    onUnsubscribe: (FcmTopic) -> Unit
) {
    KwNoticeSwitchBar(
        title = stringResource(id = fcmTopicModel.settingsLabelResId),
        checked = uiState.subscription[fcmTopicModel.topic]!!,
        onTap = {
            if (it) {
                onSubscribe(fcmTopicModel.topic)
            } else {
                onUnsubscribe(fcmTopicModel.topic)
            }
        },
        Modifier.fillMaxWidth()
    )
}

@Composable
fun SettingsTitle(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        style = KwNoticeTypography.labelMedium.copy(
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier.then(Modifier.padding(top = 12.dp))
    )
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    KwNoticeTheme {
        SettingsScreen(
            uiState = SettingsUiState.Success(
                mapOf(
                    FcmTopic.KwHomeNew to true,
                    FcmTopic.KwHomeEdit to false,
                    FcmTopic.SwCentralNew to true
                )
            ),
            onSubscribe = {},
            onUnsubscribe = {}
        )
    }
}
