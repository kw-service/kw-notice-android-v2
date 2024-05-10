package dev.yjyoon.kwnotice.presentation.ui.notice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeNoticeCard

@Composable
fun SwCentralContent(
    uiState: SwCentralNoticeUiState,
    filterState: NoticeFilterState,
    favoriteNotices: List<Favorite>,
    refreshing: Boolean,
    onClickNotice: (String) -> Unit,
    onAddToFavorite: (Notice) -> Unit,
    onDeleteFromFavorite: (Notice) -> Unit,
    onMonthFilterChange: (String?) -> Unit,
    onRefresh: () -> Unit
) {
    when (uiState) {
        is SwCentralNoticeUiState.Success -> {
            Column(Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    KwNoticeDropdownMenu(
                        leadingIconRes = R.drawable.ic_tag,
                        initialItem = stringResource(id = R.string.filter_tag_all),
                        items = listOf(),
                        onSelectItem = { }
                    )
                    Spacer(Modifier.width(8.dp))
                    KwNoticeDropdownMenu(
                        leadingIconRes = R.drawable.ic_group,
                        initialItem = stringResource(id = R.string.filter_department_all),
                        items = listOf(),
                        onSelectItem = { }
                    )
                    Spacer(Modifier.weight(1f))
                    KwNoticeDropdownMenu(
                        leadingIconRes = R.drawable.ic_calendar,
                        initialItem = stringResource(id = R.string.filter_month_all),
                        items = uiState.months.map { "$it${stringResource(id = R.string.month)}" },
                        onSelectItem = onMonthFilterChange
                    )
                }
                SwCentralNoticeColumn(
                    uiState = uiState,
                    filterState = filterState,
                    favoriteNotices = favoriteNotices,
                    refreshing = refreshing,
                    onClickNotice = onClickNotice,
                    onAddToFavorite = onAddToFavorite,
                    onDeleteFromFavorite = onDeleteFromFavorite,
                    onRefresh = onRefresh
                )
            }
        }

        SwCentralNoticeUiState.Loading -> {
            CircularProgressIndicator()
        }

        SwCentralNoticeUiState.Failure -> {
            FailureScreen(onRefresh = onRefresh)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwCentralNoticeColumn(
    uiState: SwCentralNoticeUiState.Success,
    filterState: NoticeFilterState,
    favoriteNotices: List<Favorite>,
    refreshing: Boolean,
    onClickNotice: (String) -> Unit,
    onAddToFavorite: (Notice) -> Unit,
    onDeleteFromFavorite: (Notice) -> Unit,
    onRefresh: () -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = onRefresh
    )
    Box(
        modifier = Modifier.pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 18.dp, vertical = 0.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.Top)
        ) {
            items(uiState.notices.filter { filterState.filtering(it) }) {
                KwNoticeNoticeCard(
                    notice = it,
                    onClickNotice = onClickNotice,
                    bookmarked = favoriteNotices.contains(it.toFavorite()),
                    onToggleBookmark = { notice, bookmarked ->
                        if (bookmarked) {
                            onAddToFavorite(notice)
                        } else {
                            onDeleteFromFavorite(notice)
                        }
                    }
                )
            }
            item { Spacer(modifier = Modifier.height(4.dp)) }
        }
        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = refreshing,
            state = pullRefreshState,
            contentColor = MaterialTheme.colorScheme.primary,
            scale = true
        )
    }
}
