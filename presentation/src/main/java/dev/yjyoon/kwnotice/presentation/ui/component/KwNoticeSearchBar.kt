package dev.yjyoon.kwnotice.presentation.ui.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTypography

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun KwNoticeSearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    onClose: () -> Unit,
) {
    var text by remember { mutableStateOf("") }
    val color = if (isSystemInDarkTheme()) Color.White else Color.Black

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.focusRequester(focusRequester),
        textStyle = KwNoticeTypography.bodyMedium,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(text)
                keyboardController?.hide()
            }
        ),
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        decorationBox = { innerTextField ->
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = color.copy(alpha = 0.05f)
            ) {
                Row(
                    modifier = modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(modifier = Modifier.weight(1f)) {
                        if(text.isEmpty()) {
                            Text(
                                stringResource(id = R.string.searchbar_placeholder),
                                color = color.copy(alpha = 0.15f),
                                style = KwNoticeTypography.bodyMedium,
                                maxLines = 1
                            )
                        }
                        innerTextField()
                    }
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview(showBackground = true)
@Composable
fun KwNoticeSearchBarPreview() {
    KwNoticeTheme {
        KwNoticeSearchBar(onSearch = {}, onClose = {})
    }
}
