package com.example.plantcompose.datasource.local

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Class manages Preference data store as cache.
 */
internal class PlantComposePreferenceManager(
    private val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        PREFERENCE_DATA_STORE
    )

    private suspend fun <T> DataStore<Preferences>.getCacheData(
        preferenceKey: Preferences.Key<T>
    ): Flow<T?> = data.catch {
        if (it is IOException) {
            Log.e(LOG_TAG, "Failed to read data from preference ${it.message}")
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {
        it[preferenceKey]
    }

    /**
     * Function to store value in preference data store.
     * @param key Preference key.
     * @param value value to store.
     *
     * Any exception thrown here will be catch in [PlantComposeCacheImpl].
     */
    suspend fun <T> storeValue(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit {
            it[key] = value
        }
    }

    /**
     * Function to clear given Key's data preference data store.
     * @param key Preference key.
     *
     * Any exception thrown here will be catch in [PlantComposeCacheImpl].
     */
    suspend fun <T> clearValue(key: Preferences.Key<T>) {
        context.dataStore.edit {
            it.clear()
        }
    }

    /**
     * Function to read value from preference data store.
     * @param key Preference key.
     * @return Flow of value.
     *
     * Any exception thrown here will be catch in [PlantComposeCacheImpl].
     */
    suspend fun <T> readValue(key: Preferences.Key<T>): Flow<T?> {
        return context.dataStore.getCacheData(key)
    }

    private companion object {
        private const val LOG_TAG = "PlantComposePreferenceManager"
        private const val PREFERENCE_DATA_STORE = "plants_store"
    }
}
