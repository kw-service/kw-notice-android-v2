package dev.yjyoon.kwnotice.presentation.ui.notice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dev.yjyoon.kwnotice.domain.model.Notice
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeTopAppBar
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun NoticeScreen(
    viewModel: NoticeViewModel = hiltViewModel(),
    onClickNotice: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    NoticeScreen(
        uiState = uiState,
        onRefresh = viewModel::refresh,
        isLoading = viewModel::isLoading,
        onClickNotice = onClickNotice,
        onAddToFavorite = viewModel::addFavorite,
        onDeleteFromFavorite = viewModel::deleteFavorite
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NoticeScreen(
    uiState: NoticeUiState,
    onRefresh: () -> Unit,
    isLoading: () -> Boolean,
    onClickNotice: (String) -> Unit,
    onAddToFavorite: (Notice) -> Unit,
    onDeleteFromFavorite: (Notice) -> Unit
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Box(
        Modifier.fillMaxSize()
    ) {
        Column {
            KwNoticeTopAppBar(
                titleText = stringResource(id = R.string.navigation_notice),
                actionIcon = Icons.Outlined.Search,
                onActionClick = {}
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
                            onDeleteFromFavorite = onDeleteFromFavorite
                        )
                    }
                    NoticeTab.SwCentral.ordinal -> {
                        SwCentralContent(
                            uiState = uiState.swCentralNoticeUiState,
                            onClickNotice = onClickNotice,
                            onAddToFavorite = onAddToFavorite,
                            onDeleteFromFavorite = onDeleteFromFavorite
                        )
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = onRefresh,
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            if (isLoading()) {
                CircularProgressIndicator(
                    Modifier.size(16.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
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
                    notices =
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
                    },
                    favoriteIds = emptyList()
                ),
                swCentralNoticeUiState = SwCentralNoticeUiState.Failure
            ),
            onRefresh = {},
            isLoading = { false },
            onClickNotice = {},
            onAddToFavorite = {},
            onDeleteFromFavorite = {}
        )
    }
}