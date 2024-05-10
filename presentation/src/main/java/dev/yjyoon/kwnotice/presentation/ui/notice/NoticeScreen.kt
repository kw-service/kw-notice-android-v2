package dev.yjyoon.kwnotice.presentation.ui.notice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.yjyoon.kwnotice.domain.model.Favorite
import dev.yjyoon.kwnotice.domain.model.Notice
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeSearchTopAppBar
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme
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
        refreshing = viewModel.refreshing,
        onClickNotice = onClickNotice,
        onAddToFavorite = viewModel::addFavorite,
        onDeleteFromFavorite = viewModel::deleteFavorite,
        onSearch = viewModel::setTitleFilter,
        onTagFilterChange = viewModel::setTagFilter,
        onDepartmentFilterChange = viewModel::setDepartmentFilter,
        onMonthFilterChange = viewModel::setMonthFilter,
        onInitFilter = viewModel::initFilter,
        onRefresh = viewModel::refresh
    )
}

// @OptIn(ExperimentalPagerApi::class)
@Composable
fun NoticeScreen(
    uiState: NoticeUiState,
    filterState: NoticeFilterState,
    refreshing: Boolean,
    favoriteNotices: List<Favorite>,
    onClickNotice: (String) -> Unit,
    onAddToFavorite: (Notice) -> Unit,
    onDeleteFromFavorite: (Notice) -> Unit,
    onSearch: (String) -> Unit,
    onTagFilterChange: (String?) -> Unit,
    onDepartmentFilterChange: (String?) -> Unit,
    onMonthFilterChange: (String?) -> Unit,
    onInitFilter: () -> Unit,
    onRefresh: (NoticeTab) -> Unit,
) {
    /*
     * 광운대학교의 SW중심대학사업단 사업 종료 및 빛솔재 공지사항 알림 서비스 지원의 어려움에 따라
     * v2.3.0 에서 광운대학교 홈페이지 공지사항 탭을 제외한 모든 탭을 제거.
     * 추후 유지보수를 위해 기존 코드를 제거하지 않고 주석처리 함.
     * https://github.com/kw-service/kw-notice-android-v2/issues/84
     */
//    val pagerState = rememberPagerState()
//    val coroutineScope = rememberCoroutineScope()

    Box(
        Modifier.fillMaxSize()
    ) {
        Column(
            Modifier.fillMaxSize()
        ) {
            KwNoticeSearchTopAppBar(
                titleText = stringResource(id = R.string.navigation_notice),
                onSearch = onSearch,
                onCloseSearch = onInitFilter
            )
//            TabRow(
//                selectedTabIndex = pagerState.currentPage,
//            ) {
//                NoticeTab.entries.forEachIndexed { index, tab ->
//                    Tab(
//                        text = { Text(stringResource(id = tab.textRes)) },
//                        selected = pagerState.currentPage == index,
//                        onClick = {
//                            coroutineScope.launch {
//                                pagerState.animateScrollToPage(page = index)
//                            }
//                        },
//                    )
//                }
//            }
//            HorizontalPager(
//                count = NoticeTab.entries.size,
//                state = pagerState,
//                modifier = Modifier.fillMaxSize()
//            ) { tab ->
//                when (tab) {
//                    NoticeTab.KwHome.ordinal -> {
            KwHomeContent(
                uiState = uiState.kwHomeNoticeUiState,
                onClickNotice = onClickNotice,
                onAddToFavorite = onAddToFavorite,
                onDeleteFromFavorite = onDeleteFromFavorite,
                favoriteNotices = favoriteNotices,
                filterState = filterState,
                onTagFilterChange = onTagFilterChange,
                onDepartmentFilterChange = onDepartmentFilterChange,
                onMonthFilterChange = onMonthFilterChange,
                refreshing = refreshing,
                onRefresh = { onRefresh(NoticeTab.KwHome) }
            )
        }
//
//                    NoticeTab.SwCentral.ordinal -> {
//                        SwCentralContent(
//                            uiState = uiState.swCentralNoticeUiState,
//                            onClickNotice = onClickNotice,
//                            onAddToFavorite = onAddToFavorite,
//                            onDeleteFromFavorite = onDeleteFromFavorite,
//                            favoriteNotices = favoriteNotices,
//                            filterState = filterState,
//                            onMonthFilterChange = onMonthFilterChange,
//                            refreshing = refreshing,
//                            onRefresh = { onRefresh(NoticeTab.SwCentral) }
//                        )
//                    }
//
//                    NoticeTab.KwDorm.ordinal -> {
//                        KwDormContent(
//                            uiState = uiState.kwDormNoticeUiState,
//                            onClickNotice = onClickNotice,
//                            onAddToFavorite = onAddToFavorite,
//                            onDeleteFromFavorite = onDeleteFromFavorite,
//                            favoriteNotices = favoriteNotices,
//                            filterState = filterState,
//                            onMonthFilterChange = onMonthFilterChange,
//                            refreshing = refreshing,
//                            onRefresh = { onRefresh(NoticeTab.KwDorm) }
//                        )
//                    }
//                }
//            }
//        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NoticeScreenPreview() {
    KwNoticeTheme {
        NoticeScreen(
            uiState = NoticeUiState(
                kwHomeNoticeUiState = KwHomeNoticeUiState.Success(
                    notices = List(10) {
                        Notice.KwHome(
                            id = 0,
                            title = "[학사] 2022학년도 1학기 성적 확인 및 성적증명서 발급 안내",
                            tag = "학사",
                            department = "교육지원팀",
                            url = "",
                            postedDate = LocalDate.now(),
                            modifiedDate = LocalDate.now()
                        )
                    },
                    tags = listOf("전체"),
                    departments = listOf("전체 부서"),
                    months = listOf(1)
                ),
                swCentralNoticeUiState = SwCentralNoticeUiState.Failure,
                kwDormNoticeUiState = KwDormNoticeUiState.Failure,
                favoriteNotices = emptyList()
            ),
            filterState = NoticeFilterState.Unspecified,
            favoriteNotices = emptyList(),
            refreshing = false,
            onClickNotice = {},
            onAddToFavorite = {},
            onDeleteFromFavorite = {},
            onDepartmentFilterChange = {},
            onTagFilterChange = {},
            onSearch = {},
            onMonthFilterChange = {},
            onInitFilter = {},
            onRefresh = {}
        )
    }
}