package dev.yjyoon.kwnotice.presentation.ui.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.yjyoon.kwnotice.domain.model.Favorite
import dev.yjyoon.kwnotice.presentation.R
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeLoading
import dev.yjyoon.kwnotice.presentation.ui.component.KwNoticeSearchTopAppBar
import dev.yjyoon.kwnotice.presentation.ui.theme.KwNoticeTheme
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onClickNotice: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val filterState by viewModel.filterState.collectAsState()

    FavoriteScreen(
        uiState = uiState,
        filterState = filterState,
        onSearch = viewModel::setTitleFilter,
        onClickFavorite = onClickNotice,
        onUnbookmark = viewModel::deleteFromFavorite,
        onTypeFilterChange = viewModel::setTypeFilter,
        onMonthFilterChange = viewModel::setMonthFilter,
        onInitFilter = viewModel::initFilter,
        addToFavorite = viewModel::addToFavorite
    )
}

@Composable
fun FavoriteScreen(
    uiState: FavoriteUiState,
    filterState: FavoriteFilterState,
    onSearch: (String) -> Unit,
    onClickFavorite: (String) -> Unit,
    onUnbookmark: (Favorite) -> Unit,
    onTypeFilterChange: (String?) -> Unit,
    onMonthFilterChange: (String?) -> Unit,
    onInitFilter: () -> Unit,
    addToFavorite: (Favorite) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var job: Job? by remember { mutableStateOf(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    val showUndoSnackbar: (Favorite) -> Unit = { favorite ->
        job?.cancel()
        job = scope.launch {
            val snackbarResult = snackbarHostState.showSnackbar(
                message = context.getString(R.string.unbookmarked),
                actionLabel = context.getString(R.string.undo)
            )
            when (snackbarResult) {
                SnackbarResult.ActionPerformed -> {
                    addToFavorite(favorite)
                }
                SnackbarResult.Dismissed -> {}
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                FavoriteSnackbar(data)
            }
        }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            KwNoticeSearchTopAppBar(
                titleText = stringResource(id = R.string.navigation_favorite),
                onSearch = onSearch,
                onCloseSearh = onInitFilter
            )
            Box(
                Modifier.weight(1f)
            ) {
                when (uiState) {
                    is FavoriteUiState.Success -> {
                        FavoriteContent(
                            uiState = uiState,
                            filterState = filterState,
                            onClickFavorite = onClickFavorite,
                            onUnbookmark = onUnbookmark,
                            onTypeFilterChange = onTypeFilterChange,
                            onMonthFilterChange = onMonthFilterChange,
                            showUndoSnackbar = showUndoSnackbar
                        )
                    }
                    FavoriteUiState.Empty -> {
                        FavoriteEmpty(
                            Modifier
                                .align(Alignment.Center)
                                .fillMaxWidth()
                        )
                    }
                    FavoriteUiState.Loading -> {
                        KwNoticeLoading()
                    }
                    FavoriteUiState.Failure -> {
                        // no-op
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteSnackbar(data: SnackbarData) {
    Snackbar(
        Modifier.padding(18.dp),
        action = {
            data.visuals.actionLabel?.let {
                TextButton(
                    onClick = { data.performAction() },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.inversePrimary
                    )
                ) {
                    Text(it)
                }
            }
        }
    ) {
        Text(data.visuals.message)
    }
}

@Composable
fun FavoriteEmpty(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(32.dp)
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.favorite_empty),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteScreenPreview() {
    KwNoticeTheme {
        FavoriteScreen(
            uiState = FavoriteUiState.Empty,
            filterState = FavoriteFilterState.Unspecified,
            onSearch = {},
            onInitFilter = {},
            onMonthFilterChange = {},
            onTypeFilterChange = {},
            onUnbookmark = {},
            onClickFavorite = {},
            addToFavorite = {}
        )
    }
}
