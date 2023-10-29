package aab.lib.commons.data.dataStore

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreRepositoryImpl(private val context: Context) : DataStoreRepository {

    private val Context.dataStore by preferencesDataStore(name = "prefs")

    override suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T):
            Flow<T> = context.dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        val result = preferences[key] ?: defaultValue
        result
    }

    override suspend fun <T> readPreference(key: Preferences.Key<T>, defaultValue: T): T {
        return context.dataStore.data.first()[key] ?: defaultValue
    }


    override suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        context.dataStore.edit {
            it.remove(key)
        }
    }

    override suspend fun clearAllPreference() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    override fun provideContext() = context


    @SuppressLint("HardwareIds")
    override fun deviceId(): String = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
}