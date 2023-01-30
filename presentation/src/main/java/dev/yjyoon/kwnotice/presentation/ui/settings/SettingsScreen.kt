package dev.yjyoon.kwnotice.presentation.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.domain.model.FcmTopic
import dev.yjyoon.kwnotice.domain.model.VersionName
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeDivider
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeLoading
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeSwitchBar
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeTopAppBar
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeTouchBar
import dev.yjyoon.kwnotice.presentation.ui.model.FcmTopicModel
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTypography

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onClickOsl: () -> Unit,
    versionName: VersionName
) {
    val uiState by viewModel.uiState.collectAsState()
    var openDialog by remember { mutableStateOf(false) }

    if (openDialog) {
        SettingsDialog(
            onClose = { openDialog = false },
            versionName = versionName
        )
    }

    SettingsScreen(
        uiState = uiState,
        onSubscribe = viewModel::subscribeTo,
        onUnsubscribe = viewModel::unsubscribeFrom,
        onClickOsl = onClickOsl,
        onOpenDialog = { openDialog = true },
        versionName = versionName
    )
}

@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onSubscribe: (FcmTopic) -> Unit,
    onUnsubscribe: (FcmTopic) -> Unit,
    onClickOsl: () -> Unit,
    onOpenDialog: () -> Unit,
    versionName: VersionName
) {
    when (uiState) {
        is SettingsUiState.Success -> {
            SettingsContent(
                uiState = uiState,
                onSubscribe = onSubscribe,
                onUnsubscribe = onUnsubscribe,
                onClickOsl = onClickOsl,
                onOpenDialog = onOpenDialog,
                versionName = versionName
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
    onUnsubscribe: (FcmTopic) -> Unit,
    onClickOsl: () -> Unit,
    onOpenDialog: () -> Unit,
    versionName: VersionName
) {
    val uriHandler = LocalUriHandler.current

    Column(
        Modifier.fillMaxSize()
    ) {
        KwNoticeTopAppBar(
            titleText = stringResource(id = R.string.navigation_settings),
            actionIcon = Icons.Outlined.Info,
            onActionClick = onOpenDialog
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
        KwNoticeDivider()
        SettingsTitle(
            Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.kw_dorm)
        )
        Spacer(Modifier.height(4.dp))
        FcmTopicSwitchBar(
            uiState = uiState,
            fcmTopicModel = FcmTopicModel(FcmTopic.KwDormCommon),
            onSubscribe = onSubscribe,
            onUnsubscribe = onUnsubscribe
        )
        FcmTopicSwitchBar(
            uiState = uiState,
            fcmTopicModel = FcmTopicModel(FcmTopic.KwDormRecruitment),
            onSubscribe = onSubscribe,
            onUnsubscribe = onUnsubscribe
        )
        KwNoticeDivider()
        SettingsTitle(
            Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.settings_app_info)
        )
        Spacer(Modifier.height(4.dp))
        KwNoticeTouchBar(
            title = stringResource(id = R.string.settings_dev_info),
            subtitle = stringResource(id = R.string.settings_dev_email),
            onClick = { uriHandler.openUri(SettingsViewModel.GITHUB_URL) }
        )
        KwNoticeTouchBar(
            title = stringResource(id = R.string.settings_osl),
            onClick = onClickOsl
        )
        KwNoticeTouchBar(
            title = "${stringResource(id = R.string.settings_version)} $versionName",
            isIconVisible = false,
            onClick = { uriHandler.openUri(SettingsViewModel.PLAYSTORE_URL) }
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
        style = KwNoticeTypography.labelLarge.copy(
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier.then(Modifier.padding(top = 12.dp))
    )
}

@Composable
fun SettingsDialog(
    onClose: () -> Unit,
    versionName: VersionName
) {
    AlertDialog(
        onDismissRequest = onClose,
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(64.dp)
            )
        },
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.kw_notice),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = stringResource(id = R.string.settings_dialog_ver, versionName.name),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        text = {
            Text(text = stringResource(id = R.string.settings_dialog_text))
        },
        confirmButton = {
            TextButton(onClick = onClose) {
                Text("확인")
            }
        }
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
            onUnsubscribe = {},
            onClickOsl = {},
            onOpenDialog = {},
            versionName = VersionName("2.0.0")
        )
    }
}
