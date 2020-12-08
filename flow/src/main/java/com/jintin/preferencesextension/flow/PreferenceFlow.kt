package com.jintin.preferencesextension.flow

import android.content.SharedPreferences
import com.jintin.preferencesextension.get
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@Suppress("EXPERIMENTAL_API_USAGE")
inline fun <reified T> SharedPreferences.flow(
    key: String,
    notifyInitValue: Boolean = true
): Flow<T> = callbackFlow {
    if (notifyInitValue) {
        send(get(key))
    }
    val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, target ->
        if (key == target) {
            sendBlocking(get(key))
        }
    }
    registerOnSharedPreferenceChangeListener(listener)
    awaitClose {
        unregisterOnSharedPreferenceChangeListener(listener)
    }
}