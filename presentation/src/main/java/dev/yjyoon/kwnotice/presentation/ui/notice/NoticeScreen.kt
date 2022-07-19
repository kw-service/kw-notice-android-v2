package dev.yjyoon.kwnotice.presentation.ui.notice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dev.yjyoon.kwnotice.domain.model.Favorite
import dev.yjyoon.kwnotice.domain.model.Notice
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeSearchTopAppBar
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun NoticeScreen(
    viewModel: NoticeViewModel = hiltViewModel(),
    onClickNotice: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val filterState by viewModel.filterState.collectAsState()

    NoticeScreen(
        uiState = uiState,
        filterState = filterState,
        favoriteNotices = uiState.favoriteNotices,
        onClickNotice = onClickNotice,
        onAddToFavorite = viewModel::addFavorite,
        onDeleteFromFavorite = viewModel::deleteFavorite,
        onSearch = viewModel::setTitleFilter,
        onTagFilterChange = viewModel::setTagFilter,
        onDepartmentFilterChange = viewModel::setDepartmentFilter
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NoticeScreen(
    uiState: NoticeUiState,
    filterState: NoticeFilterState,
    favoriteNotices: List<Favorite>,
    onClickNotice: (String) -> Unit,
    onAddToFavorite: (Notice) -> Unit,
    onDeleteFromFavorite: (Notice) -> Unit,
    onSearch: (String) -> Unit,
    onTagFilterChange: (String) -> Unit,
    onDepartmentFilterChange: (String) -> Unit
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    var showSearchBar by remember { mutableStateOf(false) }

    Box(
        Modifier.fillMaxSize()
    ) {
        Column {
            KwNoticeSearchTopAppBar(
                titleText = stringResource(id = R.string.navigation_notice),
                onSearch = onSearch
            )
            TabRow(
                selectedTabIndex = pagerState.currentPage,
            ) {
                NoticeTab.values().forEachIndexed { index, tab ->
                    Tab(
                        text = { Text(stringResource(id = tab.textRes)) },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(page = index)
                            }
                        },
                    )
                }
            }
            HorizontalPager(
                count = NoticeTab.values().size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { tab ->
                when (tab) {
                    NoticeTab.KwHome.ordinal -> {
                        KwHomeContent(
                            uiState = uiState.kwHomeNoticeUiState,
                            onClickNotice = onClickNotice,
                            onAddToFavorite = onAddToFavorite,
                            onDeleteFromFavorite = onDeleteFromFavorite,
                            favoriteNotices = favoriteNotices,
                            filterState = filterState
                        )
                    }
                    NoticeTab.SwCentral.ordinal -> {
                        SwCentralContent(
                            uiState = uiState.swCentralNoticeUiState,
                            onClickNotice = onClickNotice,
                            onAddToFavorite = onAddToFavorite,
                            onDeleteFromFavorite = onDeleteFromFavorite,
                            favoriteNotices = favoriteNotices,
                            filterState = filterState
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NoticeScreenPreview() {
    KwNoticeTheme {
        NoticeScreen(
            uiState = NoticeUiState(
                kwHomeNoticeUiState = KwHomeNoticeUiState.Success(
                    List(10) {
                        Notice.KwHome(
                            id = 0,
                            title = "[학사] 2022학년도 1학기 성적 확인 및 성적증명서 발급 안내",
                            tag = "학사",
                            department = "교육지원팀",
                            url = "",
                            postedDate = LocalDate.now(),
                            modifiedDate = LocalDate.now()
                        )
                    }
                ),
                swCentralNoticeUiState = SwCentralNoticeUiState.Failure,
                favoriteNotices = emptyList()
            ),
            filterState = NoticeFilterState.Unspecified,
            favoriteNotices = emptyList(),
            onClickNotice = {},
            onAddToFavorite = {},
            onDeleteFromFavorite = {},
            onDepartmentFilterChange = {},
            onTagFilterChange = {},
            onSearch = {}
        )
    }
}