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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeDivider
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeSwitchBar
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeTopAppBar
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTypography

@Composable
fun SettingsScreen() {
    Column(
        Modifier.fillMaxSize()
    ) {
        var checked1 by remember { mutableStateOf(true) }
        var checked2 by remember { mutableStateOf(true) }
        var checked3 by remember { mutableStateOf(true) }
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
        KwNoticeSwitchBar(
            title = stringResource(id = R.string.settings_notification_new),
            checked = checked1,
            onTap = { checked1 = it },
            Modifier.fillMaxWidth()
        )
        KwNoticeSwitchBar(
            title = stringResource(id = R.string.settings_notification_edit),
            checked = checked2,
            onTap = { checked2 = it },
            Modifier.fillMaxWidth()
        )
        KwNoticeDivider()
        SettingsTitle(
            Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.sw_central)
        )
        Spacer(Modifier.height(4.dp))
        KwNoticeSwitchBar(
            title = stringResource(id = R.string.settings_notification_new),
            checked = checked3,
            onTap = { checked3 = it },
            Modifier.fillMaxWidth()
        )
    }
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

@Preview
@Composable
private fun SettingsScreenPreview() {
    KwNoticeTheme {
        SettingsScreen()
    }
}
