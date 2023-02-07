package dev.yjyoon.kwnotice.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme

@Composable
fun KwNoticeSwitchBar(
    title: String,
    checked: Boolean,
    onTap: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier.then(Modifier
            .height(56.dp)
            .clickable { onTap(!checked) }
            .padding(16.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title)
        Switch(
            checked = checked,
            onCheckedChange = { onTap(it) }
        )
    }
}

@Preview(widthDp = 360, showBackground = true)
@Composable
private fun KwNoticeSwitchBarPreview() {
    KwNoticeTheme {
        KwNoticeSwitchBar(
            title = "새 공지사항 등록 알림",
            checked = true,
            onTap = { }
        )
    }
}
