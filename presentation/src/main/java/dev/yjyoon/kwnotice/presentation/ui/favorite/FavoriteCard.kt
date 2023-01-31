package dev.yjyoon.kwnotice.presentation.ui.favorite

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.domain.model.Favorite
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeBadge
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeRoundRect
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun FavoriteCard(
    favorite: Favorite,
    onClickFavorite: (String) -> Unit,
    onUnbookmark: (Favorite) -> Unit,
    showUndoSnackbar: (Favorite) -> Unit
) {
    ElevatedCard(
        onClick = { onClickFavorite(favorite.url) },
        modifier = Modifier.height(IntrinsicSize.Min)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            KwNoticeRoundRect(width = 4.dp, radius = 0.dp)
            Column(
                Modifier
                    .padding(start = 12.dp, top = 12.dp, end = 8.dp, bottom = 10.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                NoticeTitle(favorite.title)
                Spacer(Modifier.height(12.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NoticeDateBadge(favorite.date)
                    NoticeTypeBadge(favorite.type)
                }
            }
            FilledIconToggleButton(
                checked = true,
                onCheckedChange = {
                    favorite.let {
                        onUnbookmark(it)
                        showUndoSnackbar(it)
                    }
                },
                modifier = Modifier.padding(end = 12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star_filled),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun NoticeTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Composable
fun NoticeDateBadge(date: LocalDate) {
    KwNoticeBadge(
        leadingIconRes = R.drawable.ic_calendar,
        label = date.format(DateTimeFormatter.ofPattern(stringResource(id = R.string.notice_date_format)))
    )
}

@Composable
fun NoticeTypeBadge(type: Favorite.Type) {
    @StringRes val typeStringRes = when (type) {
        Favorite.Type.KwHome -> R.string.kw_home
        Favorite.Type.SwCentral -> R.string.sw_central
        Favorite.Type.KwDorm -> R.string.kw_dorm
        else -> R.string.unknown
    }

    KwNoticeBadge(leadingIconRes = R.drawable.ic_tag, label = stringResource(id = typeStringRes))
}
