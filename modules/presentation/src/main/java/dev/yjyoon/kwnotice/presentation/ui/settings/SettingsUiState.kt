package dev.yjyoon.kwnotice.presentation.ui.settings

import dev.yjyoon.kwnotice.domain.model.FcmTopic

sealed interface SettingsUiState {
    data class Success(val subscription: Map<FcmTopic, Boolean>) : SettingsUiState
    object Loading : SettingsUiState
    object Failure : SettingsUiState
}
