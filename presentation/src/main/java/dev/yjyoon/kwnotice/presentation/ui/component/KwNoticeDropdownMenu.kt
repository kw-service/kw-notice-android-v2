package dev.yjyoon.kwnotice.presentation.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme

@Composable
fun KwNoticeDropdownMenu(
    modifier: Modifier = Modifier,
    @DrawableRes leadingIconRes: Int,
    initialItem: String,
    items: List<String>,
    onSelectItem: (String?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(initialItem) }

    Box(
        modifier.wrapContentSize()
    ) {
        FilterChip(
            selected = expanded,
            onClick = { expanded = expanded.not() },
            label = {
                Text(
                    text = selectedItem,
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = leadingIconRes),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = initialItem,
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                onClick = {
                    selectedItem = initialItem
                    expanded = false
                    onSelectItem(null)
                }
            )
            items.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    onClick = {
                        selectedItem = it
                        expanded = false
                        onSelectItem(it)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun KwNoticeDropdownMenuPreview() {
    KwNoticeTheme {
        KwNoticeDropdownMenu(
            leadingIconRes = R.drawable.ic_tag,
            initialItem = "전체",
            items = listOf("일반", "학사", "등록/장학"),
            onSelectItem = {}
        )
    }
}
