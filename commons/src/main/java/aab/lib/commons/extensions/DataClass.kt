package aab.lib.commons.extensions

import android.util.Log
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

inline fun <reified T> String.toDataClass(): T? {
    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
        useArrayPolymorphism = true
    }
    return try {
        when {
            this != "" -> json.decodeFromString(serializer(),this)
            else -> null
        }
    } catch (e: Exception) {
        Log.e("JSON Error", e.localizedMessage ?: "Some exception occurred")
        null
    }
}

inline fun <reified T> String.toObjectsList(): List<T> {
    return try {
        Json.decodeFromString(this)
    } catch (e: Exception) {
        emptyList()
    }
}