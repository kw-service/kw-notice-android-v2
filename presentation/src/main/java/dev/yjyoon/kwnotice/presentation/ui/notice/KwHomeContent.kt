package dev.yjyoon.kwnotice.presentation.ui.notice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.yjyoon.kwnotice.domain.model.Favorite
import dev.yjyoon.kwnotice.domain.model.Notice
import dev.yjyoon.kwnotice.domain.model.toFavorite
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeDropdownMenu

@Composable
fun KwHomeContent(
    uiState: KwHomeNoticeUiState,
    filterState: NoticeFilterState,
    favoriteNotices: List<Favorite>,
    onClickNotice: (String) -> Unit,
    onAddToFavorite: (Notice) -> Unit,
    onDeleteFromFavorite: (Notice) -> Unit,
    onTagFilterChange: (String?) -> Unit,
    onDepartmentFilterChange: (String?) -> Unit,
    onMonthFilterChange: (String?) -> Unit
) {
    when (uiState) {
        is KwHomeNoticeUiState.Success -> {
            Column(Modifier.fillMaxSize()) {
                Row(
                    Modifier.padding(horizontal = 18.dp, vertical = 8.dp)
                ) {
                    KwNoticeDropdownMenu(
                        leadingIconRes = R.drawable.ic_tag,
                        initialItem = stringResource(id = R.string.filter_tag_all),
                        items = uiState.tags,
                        onSelectItem = onTagFilterChange
                    )
                    Spacer(Modifier.width(8.dp))
                    KwNoticeDropdownMenu(
                        leadingIconRes = R.drawable.ic_group,
                        initialItem = stringResource(id = R.string.filter_department_all),
                        items = uiState.departments,
                        onSelectItem = onDepartmentFilterChange
                    )
                    Spacer(Modifier.weight(1f))
                    KwNoticeDropdownMenu(
                        leadingIconRes = R.drawable.ic_calendar,
                        initialItem = stringResource(id = R.string.filter_month_all),
                        items = uiState.months.map { "$it${stringResource(id = R.string.month)}" },
                        onSelectItem = onMonthFilterChange
                    )
                }
                KwHomeNoticeColumn(
                    uiState = uiState,
                    filterState = filterState,
                    onClickNotice = onClickNotice,
                    onAddToFavorite = onAddToFavorite,
                    onDeleteFromFavorite = onDeleteFromFavorite,
                    favoriteNotices = favoriteNotices
                )
            }

        }
        KwHomeNoticeUiState.Loading -> {
            CircularProgressIndicator()
        }
        KwHomeNoticeUiState.Failure -> {
            FailureScreen()
        }
    }
}

@Composable
fun KwHomeNoticeColumn(
    uiState: KwHomeNoticeUiState.Success,
    filterState: NoticeFilterState,
    favoriteNotices: List<Favorite>,
    onClickNotice: (String) -> Unit,
    onAddToFavorite: (Notice) -> Unit,
    onDeleteFromFavorite: (Notice) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 0.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.Top),
    ) {
        items(uiState.notices.filter { filterState.filtering(it) }) {
            NoticeCard(
                notice = it,
                onClickNotice = onClickNotice,
                bookmarked = favoriteNotices.contains(it.toFavorite()),
                onToggleBookmark = { notice, bookmarked ->
                    if (bookmarked) {
                        onAddToFavorite(notice)
                    } else {
                        onDeleteFromFavorite(notice)
                    }
                })
        }
    }
}
