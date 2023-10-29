package aab.lib.commons.data.dataStore

import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    val accessToken = stringPreferencesKey("accessToken")
    val refreshToken = stringPreferencesKey("refreshToken")
    val user = stringPreferencesKey("user")
}