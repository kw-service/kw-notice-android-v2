package dev.yjyoon.kwnotice.presentation.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.model.OpenSourceLicense
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme

@Composable
fun KwNoticeOslBar(
    opensource: OpenSourceLicense,
    onClick: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val color = if (isSystemInDarkTheme()) Color.White else Color.Black

    OutlinedCard {
        Column(
            Modifier.padding(12.dp)
        ) {
            Text(
                text = opensource.name,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = stringResource(id = R.string.settings_copyright, opensource.copyright),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = opensource.link,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier.clickable { onClick(opensource.link) }
            )
            Spacer(Modifier.height(8.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(
                        color = color.copy(alpha = 0.05f),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                AnimatedContent(
                    targetState = expanded,
                    transitionSpec = {
                        if (targetState) {
                            slideInVertically { it } + fadeIn() with
                                    slideOutVertically { -it } + fadeOut()
                        } else {
                            slideInVertically { it } + fadeIn() with
                                    slideOutVertically { -it } + fadeOut()
                        }
                    }
                ) { targetExpanded ->
                    Text(
                        text = if (targetExpanded) {
                            opensource.type.description
                        } else {
                            opensource.type.title
                        },
                        modifier = Modifier.align(
                            Alignment.TopStart
                        ),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier
                        .then(Modifier.size(24.dp))
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        if (expanded) Icons.Default.Clear else Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KwNoticeOslBarPreview() {
    val osl = OpenSourceLicense(
        name = "accompanist-navigation-animation",
        link = "https://github.com/google/accompanist",
        copyright = "Google Inc.",
        type = OpenSourceLicense.Type.Apache2
    )
    KwNoticeTheme {
        KwNoticeOslBar(opensource = osl, onClick = {})
    }
}
