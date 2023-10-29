package aab.lib.commons.data.preferences

import android.content.Context
import aab.lib.commons.data.preferences.PreferenceHelper.clearPrefs
import aab.lib.commons.data.preferences.PreferenceHelper.get
import aab.lib.commons.data.preferences.PreferenceHelper.set

class PreferenceRepository (context: Context, prefsName: String) {

    private val prefs = PreferenceHelper.customPrefs(context = context, name = prefsName)

    fun getStringValue(tag: String) = prefs[tag, ""] ?: ""

    fun getBooleanValue(tag: String) = prefs[tag, true] ?: false

    fun getIntValue(tag: String) = prefs[tag, 0] ?: 0

    fun isValueExists(tag: String) = prefs.contains(tag)

    fun setValue (tag: String, value: Any) {
        prefs[tag] = value
    }

    fun clearPrefs() = prefs.clearPrefs()
}