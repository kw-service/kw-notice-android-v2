package dev.yjyoon.kwnotice.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import dev.yjyoon.kwnotice.domain.model.FcmTopic
import dev.yjyoon.kwnotice.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {

    override fun getFcmSettingsStream(): Flow<Map<FcmTopic, Boolean>> =
        dataStore.data.map { preferences ->
            FcmTopic.values()
                .associateBy({ it }, { preferences[booleanPreferencesKey(it.value)] ?: false })
        }

    override suspend fun subscribeFcmTopic(topic: FcmTopic): Result<Unit> = runCatching {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(topic.value)] = true
        }
    }

    override suspend fun unsubscribeFcmTopic(topic: FcmTopic): Result<Unit> = runCatching {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(topic.value)] = false
        }
    }
}
